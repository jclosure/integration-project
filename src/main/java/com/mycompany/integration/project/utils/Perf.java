package com.mycompany.integration.project.utils;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mycompany.integration.project.services.XmlProcessingService;

public class Perf {

	private static final Logger logger = LoggerFactory.getLogger(Perf.class);
	
	
	
	public static Runnable Instrament(final String name, final Runnable job)
	{
		return new Runnable()
		{
			@Override
			public void run() {

				long beginTime = System.currentTimeMillis();
				
				job.run();
				
				long endTime = System.currentTimeMillis();
				
				logger.info(String.format("%s took %s milliseconds", name, (endTime - beginTime)));
			}
		};
	}
	
	public static Callable Instrament(final String name, final Callable job)
	{
		return new Callable()
		{
			@Override
			public Object call() throws Exception {
				
				long beginTime = System.currentTimeMillis();
				
				Object result = job.call();
				
				long endTime = System.currentTimeMillis();
			
				logger.info(String.format("%s took %s milliseconds", name, (endTime - beginTime)));
				
				return result;
				
			};
		};
	}
	
}
