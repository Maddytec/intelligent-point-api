package br.com.maddytec.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.maddytec.entities.Register;
import br.com.maddytec.repositories.RegisterRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class RegisterServiceTest {

	@MockBean
	private RegisterRepository registerRepository;

	@Autowired
	private RegisterService registerService;

	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.registerRepository.findByEmployeeId(Mockito.anyLong(), Mockito.any(PageRequest.class)))
				.willReturn(new PageImpl<Register>(new ArrayList<Register>()));
		BDDMockito.given(this.registerRepository.findById(Mockito.anyLong()))
				.willReturn(Optional.ofNullable(new Register()));
		BDDMockito.given(this.registerRepository.save(Mockito.any(Register.class))).willReturn(new Register());
	}

	@Test
	public void testFindByEmployeeId() {
		Page<Register> register = this.registerService.findEmployeeById(1L, PageRequest.of(0,10));
		
		assertNotNull(register);
	}
	
	@Test
	public void testFindById() {
		Optional<Register> register =  this.registerService.findById(1L);
		
		assertTrue(register.isPresent());
	}
	
	@Test
	public void testSavingRegister() {
		Register register = this.registerService.save(new Register());
		
		assertNotNull(register);
	}

}
