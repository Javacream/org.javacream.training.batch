package org.javacream.training.batch.simplechunk;

import org.springframework.batch.core.SkipListener;
import org.springframework.stereotype.Component;

@Component
public class SimpleSkipListener implements SkipListener<String, Integer>{

	@Override
	public void onSkipInRead(Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSkipInWrite(Integer item, Throwable t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSkipInProcess(String item, Throwable t) {
		// TODO Auto-generated method stub
		
	}

}
