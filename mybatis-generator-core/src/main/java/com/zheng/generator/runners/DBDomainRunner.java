package com.zheng.generator.runners;


import com.zheng.generator.builders.TemplateModelBuilder;
import com.zheng.generator.domain.db.DBTable;
import com.zheng.generator.template.TemplateFacade;
import com.zheng.generator.utils.DBUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @Author zhenglian
 * @Date 11:43 2018/6/4
 */
@Component
@Order(value = 1)
public class DBDomainRunner implements ApplicationRunner {

    @Value("${domain.create.by.db}")
    private boolean domainByDB = false;
    @Value("${domain.db.table-names}")
    private String tableNames;

    @Autowired
    private DBUtil dbUtil;
    @Autowired
    private TemplateFacade templateFacade;
    @Autowired
    private TemplateModelBuilder modelBuilder;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (!domainByDB) {
            return;
        }
        List<DBTable> dbTables = dbUtil.getDBTables(tableNames);
        dbTables.stream()
                .filter(table -> Optional.ofNullable(table).isPresent())
                .forEach(table -> {
                    Map<String, Object> model = modelBuilder.buildDomainModel(table);
                    templateFacade.createDomainTemplate(model);
                });
    }
}
