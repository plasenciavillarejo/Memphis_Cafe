package com.memphis.cafe.controllers;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.memphis.cafe.models.service.IGeneradorQrService;

@RestController
@RequestMapping(value = "/generar")
public class GeneracionQrController {

	@Autowired
	private IGeneradorQrService generadorQrService;
	
	@GetMapping(value = "/codigoQR")
	public void generarCodigoQr(HttpServletResponse response, @RequestParam String text,
			@RequestParam(defaultValue = "350") int width,
			@RequestParam(defaultValue = "350") int heigth) throws Exception {
		
		BufferedImage image = generadorQrService.generateQRCode(text, width, heigth);
		ServletOutputStream outputStream = response.getOutputStream();
		ImageIO.write(image, "png", outputStream);
		outputStream.flush();
		outputStream.close();
	}
	
	
}
