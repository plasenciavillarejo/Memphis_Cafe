package com.memphis.cafe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.memphis.cafe.models.entity.MemphisCafe;
import com.memphis.cafe.models.service.IMemphisCafeService;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@SessionAttributes("memphisCafe")
public class MemphisController {

	private Logger logAplicacion = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IMemphisCafeService crucesService;

	@GetMapping({ "/inicio", "/" })
	public String inicio() {
		logAplicacion.info("Mostrando la carta para el Cafe Bar - Memphis");
		return "inicio";
	}

}
