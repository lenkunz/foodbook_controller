package tk.lenkyun.foodbook.server.UserManagement.SQLAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import tk.lenkyun.foodbook.foodbook.Domain.Data.Authentication.AuthenticationInfo;
import tk.lenkyun.foodbook.foodbook.Domain.Data.Authentication.UserAuthenticationInfo;
import tk.lenkyun.foodbook.foodbook.Domain.Data.Photo.PhotoItem;
import tk.lenkyun.foodbook.foodbook.Domain.Data.User.Profile;
import tk.lenkyun.foodbook.foodbook.Domain.Data.User.User;
import tk.lenkyun.foodbook.foodbook.Domain.Operation.RegistrationHelper;
import tk.lenkyun.foodbook.foodbook.Parser.rowset.RowsetParser;
import tk.lenkyun.foodbook.foodbook.Parser.rowset.UserParser;
import tk.lenkyun.foodbook.server.UserManagement.Adapter.UserAdapter;
import tk.lenkyun.foodbook.server.UserManagement.Exception.DuplicateUserException;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by lenkyun on 5/11/2558.
 */
@Repository
@PropertySource("classpath:database.properties")
public class SQLUserAdapter extends SimpleJdbcDaoSupport implements UserAdapter {
    @Autowired
    Environment env;

    @Autowired
    public SQLUserAdapter(DataSource mainDataSource){
        super();
        this.setDataSource(mainDataSource);
    }

    @Override
    public User getUserById(String id) {
        StringBuilder query = new StringBuilder();

        query.append("SELECT * FROM ")
                .append(env.getProperty("database.table.user"))
                .append(" WHERE " + UserParser.UID + " = ?");

        Object[] parameters = {Long.parseLong(id)};
        SqlRowSet result = getJdbcTemplate().queryForRowSet(query.toString(), parameters);

        if(result.next()){
            return new UserParser().from(result);
        }
        return null;
    }

    @Override
    public User getUserByUsername(String name) {
        StringBuilder query = new StringBuilder();

        query.append("SELECT * FROM ")
                .append(env.getProperty("database.table.user"))
                .append(" WHERE " + UserParser.USERNAME + " = ?");

        Object[] parameters = {name};
        SqlRowSet result = getJdbcTemplate().queryForRowSet(query.toString(), parameters);

        if(result.next()){
            return new UserParser().from(result);
        }
        return null;
    }

    @Override
    public User getUserByFacebookId(String fid) {
        StringBuilder query = new StringBuilder();

        query.append("SELECT * FROM ")
                .append(env.getProperty("database.table.user"))
                .append(" WHERE " + UserParser.SOCIALID + " = ?");

        Object[] parameters = {fid};
        SqlRowSet result = getJdbcTemplate().queryForRowSet(query.toString(), parameters);

        if(result.next()){
            return new UserParser().from(result);
        }

        return null;
    }

    @Override
    public User getUser(UserAuthenticationInfo authenticationInfo) {
        StringBuilder query = new StringBuilder();

        query.append("SELECT * FROM ")
                .append(env.getProperty("database.table.user"))
                .append(" WHERE " + UserParser.USERNAME + " = ?")
                .append(" AND " + UserParser.PASSWORD + " = ? ");

        Object[] parameters = {authenticationInfo.getId(), authenticationInfo.getInfo()};
        SqlRowSet result = getJdbcTemplate().queryForRowSet(query.toString(), parameters);

        if(result.next()){
            return new UserParser().from(result);
        }

        return null;
    }

    @Override
    public User updateUser(User user) {
        if(getUserById(user.getId()) == null)
            return null;

        StringBuilder query = new StringBuilder();
        Map<String, Object> list = new UserParser().parse(user);

        query.append("UPDATE ")
                .append(env.getProperty("database.table.user"))
                .append(" SET " + RowsetParser.getNameToValue(list))
                .append(" WHERE " + UserParser.UID + " = ? ");

        ArrayList<Object> parameters = new ArrayList<Object>();
        parameters.addAll(Arrays.asList(RowsetParser.getValueList(list)));
        parameters.add(Long.parseLong(user.getId()));

        SqlRowSet result = getJdbcTemplate().queryForRowSet(query.toString(), parameters.toArray());

        return getUserById(user.getId());
    }

    @Override
    public User createUser(RegistrationHelper registrationHelper, PhotoItem profile_picture, PhotoItem profile_cover) {
        if(getUserByUsername(registrationHelper.getUsername()) != null)
            throw new DuplicateUserException("");

        StringBuilder query = new StringBuilder();
        User user = new User(null, registrationHelper.getUsername(),
                new Profile(registrationHelper.getFirstname(), registrationHelper.getLastname(),
                    profile_picture));
        Map<String, Object> list = new UserParser().parse(user);

        query.append("INSERT INTO ")
                .append(env.getProperty("database.table.user"))
                .append(" (" + RowsetParser.getNameList(list) + ")")
                .append(" VALUES (" + RowsetParser.getValueBlanker(list) + ")");

        SqlRowSet result = getJdbcTemplate().queryForRowSet(query.toString(), RowsetParser.getValueList(list));

        return getUserByUsername(registrationHelper.getUsername());
    }

    @Override
    public void removeUser(String id) {
        StringBuilder query = new StringBuilder();

        query.append("DELETE FROM ")
                .append(env.getProperty("database.table.user"))
                .append(" WHERE " + UserParser.UID + " = ?");

        Object[] parameters = {Long.parseLong(id)};
        SqlRowSet result = getJdbcTemplate().queryForRowSet(query.toString(), parameters);
    }
}
