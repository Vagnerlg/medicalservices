package vagnerlg.com.github.medicalservices.file;

import org.springframework.data.jpa.repository.JpaRepository;

interface FileRepository extends JpaRepository<File, String> {
}
