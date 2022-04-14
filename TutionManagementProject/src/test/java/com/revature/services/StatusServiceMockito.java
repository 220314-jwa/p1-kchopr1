package com.revature.services;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.model.Status;
import com.revature.services.StatusServicesImplimentation;

@ExtendWith(MockitoExtension.class)
class StatusServiceMockito {
	
	@Mock
	StatusServicesImplimentation servive;
	@InjectMocks
	Status status=new Status(10, "NOT");

	@Test
	public void creat()
	{
		when(servive.creat(status)).thenReturn(status);
	}
	
}
