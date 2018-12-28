package br.com.maddytec.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.maddytec.dtos.RegisterDto;
import br.com.maddytec.entities.Employee;
import br.com.maddytec.entities.Register;
import br.com.maddytec.enums.TypeEnum;
import br.com.maddytec.services.EmployeeService;
import br.com.maddytec.services.RegisterService;
import br.com.maddytec.utils.UtilDate;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class RegisterControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RegisterService registerService;

	@MockBean
	private EmployeeService employeeService;

	private static final String URL_BASE = "/registers";
	private static final Long ID_EMPLOYEE = 1L;
	private static final Long ID_REGISTER = 1L;
	private static final String TYPE = TypeEnum.START_WORK.name();
	private static final Date DATE = new Date();

	@Test
	public void testSaveRigister() throws Exception {
		Register register = getDataRegister();

		BDDMockito.given(this.employeeService.findById(Mockito.anyLong())).willReturn(Optional.of(new Employee()));
		BDDMockito.given(this.registerService.save(Mockito.any(Register.class))).willReturn(register);

		mockMvc.perform(MockMvcRequestBuilders.post(URL_BASE).content(this.getJsonRequisitionPost())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.type").value(TYPE))
				.andExpect(jsonPath("$.data.date").value(UtilDate.getDate(DATE)))
				.andExpect(jsonPath("$.data.employeeId").value(ID_EMPLOYEE))
				.andExpect(jsonPath("$.errors").isEmpty());
	}

	@Test
	public void testSaveRigisterEmployeeIdInvalid() throws Exception {
		BDDMockito.given(this.employeeService.findById(Mockito.anyLong())).willReturn(Optional.empty());

		mockMvc.perform(MockMvcRequestBuilders.post(URL_BASE)
				.content(this.getJsonRequisitionPost())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors")
				.value("Employee is not found."))
				.andExpect(jsonPath("$.data").isEmpty());
	}
	
	@Test
	public void testDeleteRegister() throws Exception {
		BDDMockito.given(this.registerService.findById(Mockito.anyLong())).willReturn(Optional.of(new Register()));
		
		mockMvc.perform(MockMvcRequestBuilders.delete(URL_BASE + "/" + ID_REGISTER)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	private Register getDataRegister() {
		Register register = new Register();
		register.setDate(DATE);
		register.setType(TypeEnum.valueOf(TYPE));
		register.setEmployee(new Employee());
		register.getEmployee().setId(ID_EMPLOYEE);
		return register;
	}

	private String getJsonRequisitionPost() throws JsonProcessingException {
		RegisterDto registerDto = new RegisterDto();
		registerDto.setId(null);
		registerDto.setDate(UtilDate.getDate(DATE));
		registerDto.setType(TYPE);
		registerDto.setEmployeeId(ID_EMPLOYEE);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(registerDto);
	}

}
