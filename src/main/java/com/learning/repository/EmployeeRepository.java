package com.learning.repository;

import com.learning.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // JPQL query returning the entity
    @Query("SELECT e  FROM Employee e WHERE e.name = :name")
    public Optional<Employee> findByName(@Param("name") String empName);

    // native query returning the entity (table must have all columns mapped to Employee)
    @Query(value = "SELECT * FROM employee1 WHERE name = :name", nativeQuery = true)
    Optional<Employee> findByNameNative(@Param("name") String empName);

    // native query returning a projection (useful if selecting specific columns)
    @Query(value = "SELECT id, name, department, salary, location FROM employee1 WHERE name = :name", nativeQuery = true)
    Optional<EmployeeProjection> findProjectionByName(@Param("name") String name);
}
