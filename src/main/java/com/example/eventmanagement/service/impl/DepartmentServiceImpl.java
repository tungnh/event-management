package com.example.eventmanagement.service.impl;

import com.example.eventmanagement.domain.Department;
import com.example.eventmanagement.repository.DepartmentRepository;
import com.example.eventmanagement.service.DepartmentService;
import com.example.eventmanagement.service.dto.DepartmentDTO;
import com.example.eventmanagement.service.mapper.DepartmentMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final DepartmentMapper departmentMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    @Override
    public DepartmentDTO save(DepartmentDTO departmentDTO) {
        Department department = departmentMapper.toEntity(departmentDTO);
        department = departmentRepository.save(department);
        return departmentMapper.toDto(department);
    }

    @Override
    public Page<DepartmentDTO> findAll(Pageable pageable) {
        return departmentRepository.findAll(pageable).map(departmentMapper::toDto);
    }

    @Override
    public Optional<DepartmentDTO> findOne(Long id) {
        return departmentRepository.findById(id).map(departmentMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        departmentRepository.deleteById(id);
    }
}
