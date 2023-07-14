package com.memphis.cafe.models.service;

import java.awt.image.BufferedImage;

public interface IGeneradorQrService {

	public BufferedImage generateQRCode(String text, int width, int heith) throws Exception;
}
