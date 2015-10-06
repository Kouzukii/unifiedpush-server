/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.aerogear.unifiedpush.jpa.dao.impl;

import org.jboss.aerogear.unifiedpush.api.Category;
import org.jboss.aerogear.unifiedpush.dao.CategoryDao;

import java.util.ArrayList;
import java.util.List;

public class JPACategoryDao extends JPABaseDao<Category, Integer> implements CategoryDao {

	@Override
	public List<Category> findByNames(List<String> names) {
		List<Category> categoryList = new ArrayList<Category>();
		if(!names.isEmpty()){
			categoryList = entityManager.createQuery("select c from Category c where c.name in :names", Category.class)
					.setParameter("names", names).getResultList();
		}
		return categoryList;
	}

	@Override
	public Class<Category> getType() {
		return Category.class;
	}

	@Override
	public List<Category> findByProperty(Long propertyId) {
		List<Category> categoryList = new ArrayList<Category>();
		categoryList =  entityManager.createQuery("select c from Category c" +
				" join c.properties p" +
				" where p.id = :propertyId", Category.class)
				.setParameter("propertyId", propertyId).getResultList();
		return categoryList;
	}
	
	@Override
	public int deleteByPushApplicationID(String applicationId) {
		return entityManager.createQuery("delete from Category c " + 
				"where c.applicationId = :applicationId")
				.setParameter("applicationId", applicationId).executeUpdate();
	}

	@Override
	public List<Category> findByPushApplicationID(String pushApplicationId) {
		return entityManager.createQuery("select c from Category c "
				+ "where c.applicationId = :applicationId", Category.class)
				.setParameter("applicationId", pushApplicationId).getResultList();
	}
	
}
