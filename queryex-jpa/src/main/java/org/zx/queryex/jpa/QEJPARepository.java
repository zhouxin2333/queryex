package org.zx.queryex.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface QEJPARepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
}