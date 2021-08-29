package com.payeye.app.repositories;

import com.payeye.app.model.Address;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address, Integer> {

    @Modifying
    @Query("delete from Address a where a.employee.id =:employeeId")
    void deleteAllEmployeeAddresses(@Param("employeeId") Integer employeeId);

    @Query("from Address a where a.employee.id =:employeeId")
    List<Address> getAllEmployeeAddresses(@Param("employeeId") Integer employeeId);

}
