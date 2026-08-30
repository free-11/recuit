-- Prevent two concurrent requests from creating duplicate applications.
-- Run this against the recruit database after confirming there are no
-- duplicate student_num values.
SET @student_num_index_exists := (
    SELECT COUNT(*)
    FROM information_schema.statistics
    WHERE table_schema = DATABASE()
      AND table_name = 'student'
      AND index_name = 'uk_student_student_num'
);
SET @student_num_index_sql := IF(
    @student_num_index_exists = 0,
    'ALTER TABLE student ADD UNIQUE KEY uk_student_student_num (student_num)',
    'SELECT 1'
);
PREPARE student_num_index_statement FROM @student_num_index_sql;
EXECUTE student_num_index_statement;
DEALLOCATE PREPARE student_num_index_statement;
