package tk.lenkyun.foodbook.controller;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tk.lenkyun.foodbook.foodbook.Domain.Data.Body.ResponseDesc;
import tk.lenkyun.foodbook.foodbook.Domain.Operation.FoodPostBuilder;
import tk.lenkyun.foodbook.foodbook.Parser.json.FoodPostBuilderParser;
import tk.lenkyun.foodbook.foodbook.Parser.json.ResponseBodyParser;

/**
 * Created by lenkyun on 5/11/2558.
 */

@RestController
@RequestMapping("/post")
public class PostController {
    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody String postTest(){
        return "Hello!";
    }

    @RequestMapping(method=RequestMethod.POST)
    public @ResponseBody String postSubmit(@RequestBody String data){
        JSONObject json = new JSONObject(data);

        FoodPostBuilder foodPostBuilder = new FoodPostBuilderParser().from(json.getJSONObject("data"));
        ResponseDesc responseBody = new ResponseDesc();

        if(foodPostBuilder != null){

        }else{
            responseBody.setError(401);
            responseBody.setStatus("Request invalid.");
        }

        return new ResponseBodyParser().parse(responseBody).toString();
    }
}
