package com.xujh.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ObjectUtil {
	Properties p = new Properties();
	public Properties getObjectProperties(String prop) throws IOException {
		InputStream is = new FileInputStream(new File(prop));
		p.load(is);
		return p;
	}
}
