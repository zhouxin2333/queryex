package org.zx.queryex.mongo;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.zx.queryex.base.api.QEAbstractService;
import org.zx.queryex.base.api.QEPage;
import org.zx.queryex.base.api.QEPageQuery;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author zhouxin
 * @since 2019/8/26
 */
public class QEMongoServiceImpl<T, ID> extends QEAbstractService<T, ID, Query, CriteriaDefinition> implements QEMongoService<T, ID>, InitializingBean {

    @Autowired
    private QEMongoRepository<T, ID> mongoRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    // 当前泛型的类型
    private Class<T> tClass;

    @Override
    public Optional<T> saveOrUpdate(T t) {
        return Optional.ofNullable(mongoRepository.save(t));
    }

    @Override
    public Optional<T> findById(ID id) {
        return mongoRepository.findById(id);
    }

    @Override
    public Optional<T> findOne(Query query) {
        return Optional.ofNullable(mongoTemplate.findOne(query, this.tClass));
    }

    @Override
    public List<T> findAll(Query query) {
        return mongoTemplate.find(query, this.tClass);
    }

    @Override
    public <R extends QEPageQuery> QEPage<T> findPage(Query query, Pageable pageable) {
        query.with(pageable);

        // 查询总数
        long count = mongoTemplate.count(query, this.tClass);
        // 查询数据
        List<T> list = mongoTemplate.find(query, this.tClass);

        Page<T> page = PageableExecutionUtils.getPage(list, pageable, () -> count);
        QEPage<T> qePage = QEPage.of(page);
        return qePage;
    }

    @Override
    public long count(Query query) {
        return mongoTemplate.count(query, this.tClass);
    }

    @Override
    public void afterPropertiesSet() {
        ResolvableType[] generics = ResolvableType.forClass(this.getClass()).getSuperType().getGenerics();
        Class<T> superTypeFirstGeneric = (Class<T>) generics[0].resolve();
        if (superTypeFirstGeneric.isAnnotationPresent(Document.class)) {
            this.tClass = superTypeFirstGeneric;
        }else {
            this.tClass = (Class<T>) Arrays.stream(generics)
                                           .map(ResolvableType::resolve)
                                           .filter(targetClass -> targetClass.isAnnotationPresent(Document.class))
                                           .findFirst().get();
        }
    }
}
