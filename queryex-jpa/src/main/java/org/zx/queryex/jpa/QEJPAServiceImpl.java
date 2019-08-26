package org.zx.queryex.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.zx.queryex.base.api.QEAbstractService;
import org.zx.queryex.base.api.QEPage;
import org.zx.queryex.base.api.QEPageQuery;

import java.util.List;
import java.util.Optional;

/**
 * @author zhouxin
 * @since 2019/8/23
 */
public class QEJPAServiceImpl<T, ID> extends QEAbstractService<T, ID, Specification<T>, Specification<T>> implements QEJPAService<T, ID> {
    
    @Autowired
    private QEJPARepository<T, ID> repository;

    @Override
    public Optional<T> saveOrUpdate(T t) {
        return Optional.ofNullable(repository.saveAndFlush(t));
    }

    @Override
    public Optional<T> findOne(Specification<T> spec) {
        return repository.findOne(spec);
    }

    @Override
    public List<T> findAll(Specification<T> spec) {
        return repository.findAll(spec);
    }

    @Override
    public <R extends QEPageQuery> QEPage<T> findPage(Specification<T> spec, Pageable pageable) {
        Page<T> page = repository.findAll(spec, pageable);
        QEPage<T> qePage = QEPage.of(page);
        return qePage;
    }

    @Override
    public long count(Specification<T> spec) {
        return repository.count(spec);
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }
}
