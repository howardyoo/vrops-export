/* 
 * Copyright 2017 VMware, Inc. All Rights Reserved.
 *
 * SPDX-License-Identifier:	Apache-2.0
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vmware.vropsexport;

import com.vmware.vropsexport.sql.SQLConfig;
import com.vmware.vropsexport.wavefront.WavefrontConfig;

import java.util.regex.Matcher;

@SuppressWarnings("unused")
public class Config {


	@SuppressWarnings("unused")
    public static class Field {
		private String alias;
		private String metric;
		private String prop;
		
		public Field() {
		}
	
		public Field(String alias, String name, boolean isMetric) {
			super();
			this.alias = alias;
			if(isMetric)
				this.metric = name;
			else 
				this.prop = name;
		}

		public String getAlias() {
			return alias;
		}

		public void setAlias(String alias) {
			this.alias = alias;
		}

		public String getMetric() {
			return metric;
		}
		
		public boolean hasMetric() {
			return metric != null;
		}

		public void setMetric(String metric) {
			this.metric = metric;
		}

		public String getProp() {
			return prop;
		}

		public void setProp(String prop) {
			this.prop = prop;
		}
		
		public boolean hasProp() {
			return this.prop != null;
		}
	}

	public static final int DEFAULT_FLUSH_SIZE = 2000;

	private Field[] fields;
	private String resourceType;
	private String rollupType;
	private long rollupMinutes;
	private String dateFormat;
	private String outputFormat;
	private SQLConfig sqlConfig;
	private WavefrontConfig wavefrontConfig;
	private String resourceKind;
	private String adapterKind;
	private boolean compact;
	private String compactifyAlg = "LATEST";
	private CSVConfig csvConfig;
	private int align = 0;
	private int flushSize = DEFAULT_FLUSH_SIZE;

	public Config() {
	}

	public int getFlushSize() { return flushSize; }

	public void setFlushSize(int size) { flushSize = size; }

    	public int getAlign() {
        return align;
    }

    	public void setAlign(int align) {
        this.align = align;
    }

    	public Field[] getFields() {
		return fields;
	}

	public void setFields(Field[] fields) {
		this.fields = fields;
	}

	public void setResourceType(String resourceType) {
		Matcher m = Patterns.adapterAndResourceKindPattern.matcher(resourceType);
		if(m.matches()) {
			this.adapterKind = m.group(1);
			this.resourceKind = m.group(2);
		} else
			this.resourceKind = resourceType;
	}

	public String getRollupType() {
		return rollupType;
	}

	public void setRollupType(String rollupType) {
		this.rollupType = rollupType;
	}

	public long getRollupMinutes() {
		return rollupMinutes;
	}

	public void setRollupMinutes(long rollup) {
		this.rollupMinutes = rollup;
	}
	
	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getOutputFormat() {
		return outputFormat;
	}

	public void setOutputFormat(String outputFormat) {
		this.outputFormat = outputFormat;
	}

	public SQLConfig getSqlConfig() {
		return sqlConfig;
	}

	public void setSqlConfig(SQLConfig sqlConfig) {
		this.sqlConfig = sqlConfig;
	}

	public WavefrontConfig getWavefrontConfig() {
		return wavefrontConfig;
	}

	public void setWavefrontConfig(WavefrontConfig wavefrontConfig) {
		this.wavefrontConfig = wavefrontConfig;
	}

	public boolean hasProps() {
		for(Field f : fields) {
			if(f.hasProp())
				return true;
		}
		return false;
	}
	
	public String getResourceKind() {
		return resourceKind;
	}

	public void setResourceKind(String resourceKind) {
		this.resourceKind = resourceKind;
	}

	public String getAdapterKind() {
		return adapterKind;
	}

	public void setAdapterKind(String adapterKind) {
		this.adapterKind = adapterKind;
	}

	public boolean hasMetrics() {
		for(Field f : fields) {
			if(f.hasMetric())
				return true;
		}
		return false;
	}
	
	public CSVConfig getCsvConfig() {
		return csvConfig;
	}

	public void setCsvConfig(CSVConfig csvConfig) {
		this.csvConfig = csvConfig;
	}

	public String getResourceType() {
		return resourceType;
	}

	public boolean isCompact() {
		return compact;
	}

	public void setCompact(boolean compact) {
		this.compact = compact;
	}

	public String getCompactifyAlg() {
		return compactifyAlg;
	}

	public void setCompactifyAlg(String compactifyAlg) {
		this.compactifyAlg = compactifyAlg;
	}
}
