package br.com.maddytec.repositories;

import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.maddytec.entities.Register;

@Transactional(readOnly = true)
@NamedQueries({
	@NamedQuery(name = "RegisterRepository.findByEmployeeId",
			query = "SELECT register FROM Register register WHERE register.employee.id = :employeeId")})
public interface RegisterRepository extends JpaRepository<Register, Long> {
	
	List<Register> findByEmployeeId(@Param("employeeId") Long employeeId);
	
	Page<Register> findByEmployeeId(@Param("employeeId") Long employeeId, Pageable pageable);
	
	
}
