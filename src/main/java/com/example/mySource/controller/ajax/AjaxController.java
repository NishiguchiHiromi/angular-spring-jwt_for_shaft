package com.example.mySource.controller.ajax;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mySource.abstractclass.AbstractController;
import com.example.mySource.entity.MstStudent;
import com.example.mySource.service.SampleService;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
public class AjaxController extends AbstractController {

	@Autowired
	private SampleService sampleService;

	@RequestMapping("/userInfo")
	public String index(Model model) {
		MstStudent loginUser = sampleService.getLoginMstStudent();
		return String.valueOf(loginUser.getId());
	}

	@RequestMapping(path = "/square", method = RequestMethod.POST)
	public Object newName(@RequestBody Integer num) {
		return num * num;
	}
	
    @GetMapping("/serverError")
    public String serverError() {
        int a = 1/0;
        return "ここまでこれません！";
    }
	
	@PostMapping("/errorInfo")
	public HashMap<String, String> clientError(@RequestBody HashMap<String, Object> error) {
		error.entrySet().stream().forEach(e -> log.error(e.getKey() + ": {}", e.getValue()));

		return new HashMap<String, String>() {
			{
				put("result", "sendError success!!!");
			}
		};
	}
}
