package com.gzs.learn.backend.admin.core.tags;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.gzs.learn.backend.admin.common.Consts;
import com.gzs.learn.backend.admin.utils.LoggerUtils;
import com.gzs.learn.backend.admin.utils.SpringContextUtil;

import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

@Component("apiTemplateModel")
public class APITemplateModel extends WYFTemplateModel {
    @Override
    @SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
    protected Map<String, TemplateModel> putValue(Map params) throws TemplateModelException {
        Map<String, TemplateModel> paramWrap = null;
        if (null != params && params.size() != 0 || null != params.get(Consts.TARGET)) {
            paramWrap = new HashMap<String, TemplateModel>(params);
            SuperCustomTag tag = SpringContextUtil.getBean(SuperCustomTag.class);

            Object result = tag.result(params);
            paramWrap.put(Consts.OUT_TAG_NAME, DEFAULT_WRAPPER.wrap(result));
        } else {
            LoggerUtils.error(getClass(), "Cannot be null, must include a 'name' attribute!");
        }
        return paramWrap;
    }

}
