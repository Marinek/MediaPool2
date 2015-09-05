package de.mediapool.server.core.builder.ember;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.atteo.evo.inflector.English;
import org.springframework.util.Assert;

import com.google.common.base.CaseFormat;

import de.mediapool.server.core.builder.BuilderPattern;

/**
 * This class is based on the aricle from http://springember.blogspot.com.au/2014/08/using-ember-data-restadapter-with.html.
 * 
 * @author marcinek
 *
 */
public final class EmberModel extends ConcurrentHashMap<String, Object> {

	private static final long serialVersionUID = 1L;

	private EmberModel() {
    }

    public static class Builder<T> implements BuilderPattern<EmberModel> {
        private final ConcurrentMap<String, Object> sideLoadedItems = new ConcurrentHashMap<String, Object>();
        private final ConcurrentMap<String, Object> metaData = new ConcurrentHashMap<String, Object>();

        public Builder(final Object entity) {
            Assert.notNull(entity);
            sideLoad(entity);
        }

        public Builder(final Class<T> clazz, final Collection<T> entities) {
            Assert.notNull(entities);
            sideLoad(clazz, entities);
        }

        public Builder(final Class<T> clazz, final Iterable<T> entities) {
        	Assert.notNull(entities);
        	
        	Collection<T> collectionEntites = new ArrayList<>();
        	
        	for(T entity : entities) {
        		collectionEntites.add(entity);
        	}
        	
        	sideLoad(clazz, collectionEntites);
        }

        public Builder<T> addMeta(final String key, final Object value) {
            metaData.put(key, value);
            return this;
        }

        public Builder<T> sideLoad(final Object entity) {
            if (entity != null) {
                sideLoadedItems.put(getSingularName(entity.getClass()), entity);
            }
            return this;
        }

        public <K> Builder<T> sideLoad(final Class<K> clazz, final Collection<K> entities) {
            if (entities != null) {
                sideLoadedItems.put(getPluralName(clazz), entities);
            }
            return this;
        }

        private String getSingularName(final Class<?> clazz) {
            Assert.notNull(clazz);
            
            if(clazz.isAnnotationPresent(EmberTypeInfo.class)) {
            	EmberTypeInfo emberTypeInfo = clazz.getAnnotation(EmberTypeInfo.class);

            	return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, emberTypeInfo.name());
            }
            
            return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, clazz.getSimpleName());
        }

        private String getPluralName(final Class<?> clazz) {
            return English.plural(getSingularName(clazz));
        }

        @Override
        public EmberModel build() {
            if (metaData.size() > 0) {
                sideLoadedItems.put("meta", metaData);
            }
            EmberModel sideLoader = new EmberModel();
            sideLoader.putAll(sideLoadedItems);
            return sideLoader;
        }
    }
}