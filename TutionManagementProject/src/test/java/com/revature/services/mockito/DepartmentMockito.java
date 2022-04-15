package com.revature.services.mockito;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.revature.model.Department;
import com.revature.services.DepartmentServicesImplimentation;


class DepartmentMockito {


	@Mock
	DepartmentServicesImplimentation servive;
	
	@Mock
	Department dep;

	@Test
	public void creat()
	{
		dep.setId(1);
		dep.setDep_head_id(1);
		dep.setDept_name("Accountant");
		dep.setDeptid(1);
		 when(servive.creat(dep)).thenReturn(dep);
		
	}
	
	@Test
	public void get()
	{
		dep.setId(1);
		dep.setDep_head_id(1);
		dep.setDept_name("Accountant");
		dep.setDeptid(1);
		when(servive.get(1)).thenReturn(dep);
	}
	
	@Test
	public void delete()
	{
		dep.setId(1);
		dep.setDep_head_id(1);
		dep.setDept_name("Accountant");
		dep.setDeptid(1);
		when(servive.delete(1)).thenReturn("Deleted Succussfully :1");
	}
	
}