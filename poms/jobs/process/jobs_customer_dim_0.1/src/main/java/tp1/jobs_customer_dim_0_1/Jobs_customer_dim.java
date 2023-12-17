// ============================================================================
//
// Copyright (c) 2006-2015, Talend SA
//
// Ce code source a été automatiquement généré par_Talend Open Studio for Data Integration
// / Soumis à la Licence Apache, Version 2.0 (la "Licence") ;
// votre utilisation de ce fichier doit respecter les termes de la Licence.
// Vous pouvez obtenir une copie de la Licence sur
// http://www.apache.org/licenses/LICENSE-2.0
// 
// Sauf lorsqu'explicitement prévu par la loi en vigueur ou accepté par écrit, le logiciel
// distribué sous la Licence est distribué "TEL QUEL",
// SANS GARANTIE OU CONDITION D'AUCUNE SORTE, expresse ou implicite.
// Consultez la Licence pour connaître la terminologie spécifique régissant les autorisations et
// les limites prévues par la Licence.

package tp1.jobs_customer_dim_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

@SuppressWarnings("unused")

/**
 * Job: Jobs_customer_dim Purpose: <br>
 * Description: <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class Jobs_customer_dim implements TalendJob {

	protected static void logIgnoredError(String message, Throwable cause) {
		System.err.println(message);
		if (cause != null) {
			cause.printStackTrace();
		}

	}

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	// contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String, String> propertyTypes = new java.util.HashMap<>();

		public PropertiesWithType(java.util.Properties properties) {
			super(properties);
		}

		public PropertiesWithType() {
			super();
		}

		public void setContextType(String key, String type) {
			propertyTypes.put(key, type);
		}

		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

		}

		// if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if (NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "Jobs_customer_dim";
	private final String projectName = "TP1";
	public Integer errorCode = null;
	private String currentComponent = "";

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	private RunStat runStat = new RunStat();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	public void setDataSourceReferences(List serviceReferences) throws Exception {

		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();

		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils
				.getServices(serviceReferences, javax.sql.DataSource.class).entrySet()) {
			dataSources.put(entry.getKey(), entry.getValue());
			talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;
		private String currentComponent = null;
		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null && currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					Jobs_customer_dim.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(Jobs_customer_dim.this, new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
					}
				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tDBInput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputExcel_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class customer_dimStruct implements routines.system.IPersistableRow<customer_dimStruct> {
		final static byte[] commonByteArrayLock_TP1_Jobs_customer_dim = new byte[0];
		static byte[] commonByteArray_TP1_Jobs_customer_dim = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String Employee_ID;

		public String getEmployee_ID() {
			return this.Employee_ID;
		}

		public String Salary;

		public String getSalary() {
			return this.Salary;
		}

		public String Job_Title;

		public String getJob_Title() {
			return this.Job_Title;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.Employee_ID == null) ? 0 : this.Employee_ID.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final customer_dimStruct other = (customer_dimStruct) obj;

			if (this.Employee_ID == null) {
				if (other.Employee_ID != null)
					return false;

			} else if (!this.Employee_ID.equals(other.Employee_ID))

				return false;

			return true;
		}

		public void copyDataTo(customer_dimStruct other) {

			other.Employee_ID = this.Employee_ID;
			other.Salary = this.Salary;
			other.Job_Title = this.Job_Title;

		}

		public void copyKeysDataTo(customer_dimStruct other) {

			other.Employee_ID = this.Employee_ID;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TP1_Jobs_customer_dim.length) {
					if (length < 1024 && commonByteArray_TP1_Jobs_customer_dim.length == 0) {
						commonByteArray_TP1_Jobs_customer_dim = new byte[1024];
					} else {
						commonByteArray_TP1_Jobs_customer_dim = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TP1_Jobs_customer_dim, 0, length);
				strReturn = new String(commonByteArray_TP1_Jobs_customer_dim, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TP1_Jobs_customer_dim.length) {
					if (length < 1024 && commonByteArray_TP1_Jobs_customer_dim.length == 0) {
						commonByteArray_TP1_Jobs_customer_dim = new byte[1024];
					} else {
						commonByteArray_TP1_Jobs_customer_dim = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TP1_Jobs_customer_dim, 0, length);
				strReturn = new String(commonByteArray_TP1_Jobs_customer_dim, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TP1_Jobs_customer_dim) {

				try {

					int length = 0;

					this.Employee_ID = readString(dis);

					this.Salary = readString(dis);

					this.Job_Title = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TP1_Jobs_customer_dim) {

				try {

					int length = 0;

					this.Employee_ID = readString(dis);

					this.Salary = readString(dis);

					this.Job_Title = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Employee_ID, dos);

				// String

				writeString(this.Salary, dos);

				// String

				writeString(this.Job_Title, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Employee_ID, dos);

				// String

				writeString(this.Salary, dos);

				// String

				writeString(this.Job_Title, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Employee_ID=" + Employee_ID);
			sb.append(",Salary=" + Salary);
			sb.append(",Job_Title=" + Job_Title);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(customer_dimStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Employee_ID, other.Employee_ID);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class customerStruct implements routines.system.IPersistableRow<customerStruct> {
		final static byte[] commonByteArrayLock_TP1_Jobs_customer_dim = new byte[0];
		static byte[] commonByteArray_TP1_Jobs_customer_dim = new byte[0];

		public String Employee_ID;

		public String getEmployee_ID() {
			return this.Employee_ID;
		}

		public String Employee_Country;

		public String getEmployee_Country() {
			return this.Employee_Country;
		}

		public String Company;

		public String getCompany() {
			return this.Company;
		}

		public String Department;

		public String getDepartment() {
			return this.Department;
		}

		public String Section;

		public String getSection() {
			return this.Section;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TP1_Jobs_customer_dim.length) {
					if (length < 1024 && commonByteArray_TP1_Jobs_customer_dim.length == 0) {
						commonByteArray_TP1_Jobs_customer_dim = new byte[1024];
					} else {
						commonByteArray_TP1_Jobs_customer_dim = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TP1_Jobs_customer_dim, 0, length);
				strReturn = new String(commonByteArray_TP1_Jobs_customer_dim, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TP1_Jobs_customer_dim.length) {
					if (length < 1024 && commonByteArray_TP1_Jobs_customer_dim.length == 0) {
						commonByteArray_TP1_Jobs_customer_dim = new byte[1024];
					} else {
						commonByteArray_TP1_Jobs_customer_dim = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TP1_Jobs_customer_dim, 0, length);
				strReturn = new String(commonByteArray_TP1_Jobs_customer_dim, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TP1_Jobs_customer_dim) {

				try {

					int length = 0;

					this.Employee_ID = readString(dis);

					this.Employee_Country = readString(dis);

					this.Company = readString(dis);

					this.Department = readString(dis);

					this.Section = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TP1_Jobs_customer_dim) {

				try {

					int length = 0;

					this.Employee_ID = readString(dis);

					this.Employee_Country = readString(dis);

					this.Company = readString(dis);

					this.Department = readString(dis);

					this.Section = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Employee_ID, dos);

				// String

				writeString(this.Employee_Country, dos);

				// String

				writeString(this.Company, dos);

				// String

				writeString(this.Department, dos);

				// String

				writeString(this.Section, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Employee_ID, dos);

				// String

				writeString(this.Employee_Country, dos);

				// String

				writeString(this.Company, dos);

				// String

				writeString(this.Department, dos);

				// String

				writeString(this.Section, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Employee_ID=" + Employee_ID);
			sb.append(",Employee_Country=" + Employee_Country);
			sb.append(",Company=" + Company);
			sb.append(",Department=" + Department);
			sb.append(",Section=" + Section);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(customerStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class after_tDBInput_1Struct implements routines.system.IPersistableRow<after_tDBInput_1Struct> {
		final static byte[] commonByteArrayLock_TP1_Jobs_customer_dim = new byte[0];
		static byte[] commonByteArray_TP1_Jobs_customer_dim = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String Employee_ID;

		public String getEmployee_ID() {
			return this.Employee_ID;
		}

		public String Employee_Country;

		public String getEmployee_Country() {
			return this.Employee_Country;
		}

		public String Company;

		public String getCompany() {
			return this.Company;
		}

		public String Department;

		public String getDepartment() {
			return this.Department;
		}

		public String Section;

		public String getSection() {
			return this.Section;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.Employee_ID == null) ? 0 : this.Employee_ID.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final after_tDBInput_1Struct other = (after_tDBInput_1Struct) obj;

			if (this.Employee_ID == null) {
				if (other.Employee_ID != null)
					return false;

			} else if (!this.Employee_ID.equals(other.Employee_ID))

				return false;

			return true;
		}

		public void copyDataTo(after_tDBInput_1Struct other) {

			other.Employee_ID = this.Employee_ID;
			other.Employee_Country = this.Employee_Country;
			other.Company = this.Company;
			other.Department = this.Department;
			other.Section = this.Section;

		}

		public void copyKeysDataTo(after_tDBInput_1Struct other) {

			other.Employee_ID = this.Employee_ID;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TP1_Jobs_customer_dim.length) {
					if (length < 1024 && commonByteArray_TP1_Jobs_customer_dim.length == 0) {
						commonByteArray_TP1_Jobs_customer_dim = new byte[1024];
					} else {
						commonByteArray_TP1_Jobs_customer_dim = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TP1_Jobs_customer_dim, 0, length);
				strReturn = new String(commonByteArray_TP1_Jobs_customer_dim, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TP1_Jobs_customer_dim.length) {
					if (length < 1024 && commonByteArray_TP1_Jobs_customer_dim.length == 0) {
						commonByteArray_TP1_Jobs_customer_dim = new byte[1024];
					} else {
						commonByteArray_TP1_Jobs_customer_dim = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TP1_Jobs_customer_dim, 0, length);
				strReturn = new String(commonByteArray_TP1_Jobs_customer_dim, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TP1_Jobs_customer_dim) {

				try {

					int length = 0;

					this.Employee_ID = readString(dis);

					this.Employee_Country = readString(dis);

					this.Company = readString(dis);

					this.Department = readString(dis);

					this.Section = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TP1_Jobs_customer_dim) {

				try {

					int length = 0;

					this.Employee_ID = readString(dis);

					this.Employee_Country = readString(dis);

					this.Company = readString(dis);

					this.Department = readString(dis);

					this.Section = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Employee_ID, dos);

				// String

				writeString(this.Employee_Country, dos);

				// String

				writeString(this.Company, dos);

				// String

				writeString(this.Department, dos);

				// String

				writeString(this.Section, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Employee_ID, dos);

				// String

				writeString(this.Employee_Country, dos);

				// String

				writeString(this.Company, dos);

				// String

				writeString(this.Department, dos);

				// String

				writeString(this.Section, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Employee_ID=" + Employee_ID);
			sb.append(",Employee_Country=" + Employee_Country);
			sb.append(",Company=" + Company);
			sb.append(",Department=" + Department);
			sb.append(",Section=" + Section);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(after_tDBInput_1Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Employee_ID, other.Employee_ID);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tDBInput_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				tFileInputExcel_1Process(globalMap);

				customerStruct customer = new customerStruct();
				customer_dimStruct customer_dim = new customer_dimStruct();

				/**
				 * [tDBOutput_1 begin ] start
				 */

				ok_Hash.put("tDBOutput_1", false);
				start_Hash.put("tDBOutput_1", System.currentTimeMillis());

				currentComponent = "tDBOutput_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "customer_dim");
				}

				int tos_count_tDBOutput_1 = 0;

				int nb_line_tDBOutput_1 = 0;
				int nb_line_update_tDBOutput_1 = 0;
				int nb_line_inserted_tDBOutput_1 = 0;
				int nb_line_deleted_tDBOutput_1 = 0;
				int nb_line_rejected_tDBOutput_1 = 0;

				int deletedCount_tDBOutput_1 = 0;
				int updatedCount_tDBOutput_1 = 0;
				int insertedCount_tDBOutput_1 = 0;
				int rowsToCommitCount_tDBOutput_1 = 0;
				int rejectedCount_tDBOutput_1 = 0;

				String tableName_tDBOutput_1 = "sortie";
				boolean whetherReject_tDBOutput_1 = false;

				java.util.Calendar calendar_tDBOutput_1 = java.util.Calendar.getInstance();
				calendar_tDBOutput_1.set(1, 0, 1, 0, 0, 0);
				long year1_tDBOutput_1 = calendar_tDBOutput_1.getTime().getTime();
				calendar_tDBOutput_1.set(10000, 0, 1, 0, 0, 0);
				long year10000_tDBOutput_1 = calendar_tDBOutput_1.getTime().getTime();
				long date_tDBOutput_1;

				java.sql.Connection conn_tDBOutput_1 = null;

				String properties_tDBOutput_1 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
				if (properties_tDBOutput_1 == null || properties_tDBOutput_1.trim().length() == 0) {
					properties_tDBOutput_1 = "rewriteBatchedStatements=true&allowLoadLocalInfile=true";
				} else {
					if (!properties_tDBOutput_1.contains("rewriteBatchedStatements=")) {
						properties_tDBOutput_1 += "&rewriteBatchedStatements=true";
					}

					if (!properties_tDBOutput_1.contains("allowLoadLocalInfile=")) {
						properties_tDBOutput_1 += "&allowLoadLocalInfile=true";
					}
				}

				String url_tDBOutput_1 = "jdbc:mariadb://" + "localhost" + ":" + "3306" + "/" + "orion" + "?"
						+ properties_tDBOutput_1;

				String driverClass_tDBOutput_1 = "org.mariadb.jdbc.Driver";

				String dbUser_tDBOutput_1 = "root";

				final String decryptedPassword_tDBOutput_1 = routines.system.PasswordEncryptUtil
						.decryptPassword("enc:routine.encryption.key.v1:lj9sliE0UAmcoINnSdhcVys3yDPqyyWZwKbn7g==");

				String dbPwd_tDBOutput_1 = decryptedPassword_tDBOutput_1;
				java.lang.Class.forName(driverClass_tDBOutput_1);

				conn_tDBOutput_1 = java.sql.DriverManager.getConnection(url_tDBOutput_1, dbUser_tDBOutput_1,
						dbPwd_tDBOutput_1);

				resourceMap.put("conn_tDBOutput_1", conn_tDBOutput_1);
				conn_tDBOutput_1.setAutoCommit(false);
				int commitEvery_tDBOutput_1 = 10000;
				int commitCounter_tDBOutput_1 = 0;

				int count_tDBOutput_1 = 0;

				java.sql.DatabaseMetaData dbMetaData_tDBOutput_1 = conn_tDBOutput_1.getMetaData();
				java.sql.ResultSet rsTable_tDBOutput_1 = dbMetaData_tDBOutput_1.getTables("orion", null, null,
						new String[] { "TABLE" });
				boolean whetherExist_tDBOutput_1 = false;
				while (rsTable_tDBOutput_1.next()) {
					String table_tDBOutput_1 = rsTable_tDBOutput_1.getString("TABLE_NAME");
					if (table_tDBOutput_1.equalsIgnoreCase("sortie")) {
						whetherExist_tDBOutput_1 = true;
						break;
					}
				}
				if (whetherExist_tDBOutput_1) {
					try (java.sql.Statement stmtDrop_tDBOutput_1 = conn_tDBOutput_1.createStatement()) {
						stmtDrop_tDBOutput_1.execute("DROP TABLE `" + tableName_tDBOutput_1 + "`");
					}
				}
				try (java.sql.Statement stmtCreate_tDBOutput_1 = conn_tDBOutput_1.createStatement()) {
					stmtCreate_tDBOutput_1.execute("CREATE TABLE `" + tableName_tDBOutput_1
							+ "`(`Employee_ID` VARCHAR(10)   not null ,`Salary` VARCHAR(50)   not null ,`Job_Title` VARCHAR(23)   not null ,primary key(`Employee_ID`))");
				}

				String insert_tDBOutput_1 = "INSERT INTO `" + "sortie"
						+ "` (`Employee_ID`,`Salary`,`Job_Title`) VALUES (?,?,?)";
				int batchSize_tDBOutput_1 = 100;
				int batchSizeCounter_tDBOutput_1 = 0;

				java.sql.PreparedStatement pstmt_tDBOutput_1 = conn_tDBOutput_1.prepareStatement(insert_tDBOutput_1);
				resourceMap.put("pstmt_tDBOutput_1", pstmt_tDBOutput_1);

				/**
				 * [tDBOutput_1 begin ] stop
				 */

				/**
				 * [tMap_1 begin ] start
				 */

				ok_Hash.put("tMap_1", false);
				start_Hash.put("tMap_1", System.currentTimeMillis());

				currentComponent = "tMap_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "customer");
				}

				int tos_count_tMap_1 = 0;

// ###############################
// # Lookup's keys initialization

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row1Struct> tHash_Lookup_row1 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row1Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row1Struct>) globalMap
						.get("tHash_Lookup_row1"));

				tHash_Lookup_row1.initGet();

				row1Struct row1HashKey = new row1Struct();
				row1Struct row1Default = new row1Struct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_1__Struct {
				}
				Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
				customer_dimStruct customer_dim_tmp = new customer_dimStruct();
// ###############################

				/**
				 * [tMap_1 begin ] stop
				 */

				/**
				 * [tDBInput_1 begin ] start
				 */

				ok_Hash.put("tDBInput_1", false);
				start_Hash.put("tDBInput_1", System.currentTimeMillis());

				currentComponent = "tDBInput_1";

				int tos_count_tDBInput_1 = 0;

				java.util.Calendar calendar_tDBInput_1 = java.util.Calendar.getInstance();
				calendar_tDBInput_1.set(0, 0, 0, 0, 0, 0);
				java.util.Date year0_tDBInput_1 = calendar_tDBInput_1.getTime();
				int nb_line_tDBInput_1 = 0;
				java.sql.Connection conn_tDBInput_1 = null;
				String driverClass_tDBInput_1 = "com.mysql.jdbc.Driver";
				java.lang.Class jdbcclazz_tDBInput_1 = java.lang.Class.forName(driverClass_tDBInput_1);
				String dbUser_tDBInput_1 = "root";

				final String decryptedPassword_tDBInput_1 = routines.system.PasswordEncryptUtil
						.decryptPassword("enc:routine.encryption.key.v1:v9PcJ9I9GTAmJey9K1WyxfzDChaX4WoL4b3QXQ==");

				String dbPwd_tDBInput_1 = decryptedPassword_tDBInput_1;

				String properties_tDBInput_1 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
				if (properties_tDBInput_1 == null || properties_tDBInput_1.trim().length() == 0) {
					properties_tDBInput_1 = "";
				}
				String url_tDBInput_1 = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "orion" + "?"
						+ properties_tDBInput_1;

				conn_tDBInput_1 = java.sql.DriverManager.getConnection(url_tDBInput_1, dbUser_tDBInput_1,
						dbPwd_tDBInput_1);

				java.sql.Statement stmt_tDBInput_1 = conn_tDBInput_1.createStatement();

				String dbquery_tDBInput_1 = "select * from organization_dim";

				globalMap.put("tDBInput_1_QUERY", dbquery_tDBInput_1);
				java.sql.ResultSet rs_tDBInput_1 = null;

				try {
					rs_tDBInput_1 = stmt_tDBInput_1.executeQuery(dbquery_tDBInput_1);
					java.sql.ResultSetMetaData rsmd_tDBInput_1 = rs_tDBInput_1.getMetaData();
					int colQtyInRs_tDBInput_1 = rsmd_tDBInput_1.getColumnCount();

					String tmpContent_tDBInput_1 = null;

					while (rs_tDBInput_1.next()) {
						nb_line_tDBInput_1++;

						if (colQtyInRs_tDBInput_1 < 1) {
							customer.Employee_ID = null;
						} else {

							customer.Employee_ID = routines.system.JDBCUtil.getString(rs_tDBInput_1, 1, false);
						}
						if (colQtyInRs_tDBInput_1 < 2) {
							customer.Employee_Country = null;
						} else {

							customer.Employee_Country = routines.system.JDBCUtil.getString(rs_tDBInput_1, 2, false);
						}
						if (colQtyInRs_tDBInput_1 < 3) {
							customer.Company = null;
						} else {

							customer.Company = routines.system.JDBCUtil.getString(rs_tDBInput_1, 3, false);
						}
						if (colQtyInRs_tDBInput_1 < 4) {
							customer.Department = null;
						} else {

							customer.Department = routines.system.JDBCUtil.getString(rs_tDBInput_1, 4, false);
						}
						if (colQtyInRs_tDBInput_1 < 5) {
							customer.Section = null;
						} else {

							customer.Section = routines.system.JDBCUtil.getString(rs_tDBInput_1, 5, false);
						}

						/**
						 * [tDBInput_1 begin ] stop
						 */

						/**
						 * [tDBInput_1 main ] start
						 */

						currentComponent = "tDBInput_1";

						tos_count_tDBInput_1++;

						/**
						 * [tDBInput_1 main ] stop
						 */

						/**
						 * [tDBInput_1 process_data_begin ] start
						 */

						currentComponent = "tDBInput_1";

						/**
						 * [tDBInput_1 process_data_begin ] stop
						 */

						/**
						 * [tMap_1 main ] start
						 */

						currentComponent = "tMap_1";

						if (execStat) {
							runStat.updateStatOnConnection(iterateId, 1, 1

									, "customer"

							);
						}

						boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;

						// ###############################
						// # Input tables (lookups)
						boolean rejectedInnerJoin_tMap_1 = false;
						boolean mainRowRejected_tMap_1 = false;

						///////////////////////////////////////////////
						// Starting Lookup Table "row1"
						///////////////////////////////////////////////

						boolean forceLooprow1 = false;

						row1Struct row1ObjectFromLookup = null;

						if (!rejectedInnerJoin_tMap_1) { // G_TM_M_020

							tHash_Lookup_row1.lookup(row1HashKey);

							if (!tHash_Lookup_row1.hasNext()) { // G_TM_M_090

								forceLooprow1 = true;

							} // G_TM_M_090

						} // G_TM_M_020

						else { // G 20 - G 21
							forceLooprow1 = true;
						} // G 21

						row1Struct row1 = null;

						while ((tHash_Lookup_row1 != null && tHash_Lookup_row1.hasNext()) || forceLooprow1) { // G_TM_M_043

							// CALL close loop of lookup 'row1'

							row1Struct fromLookup_row1 = null;
							row1 = row1Default;

							if (!forceLooprow1) { // G 46

								fromLookup_row1 = tHash_Lookup_row1.next();

								if (fromLookup_row1 != null) {
									row1 = fromLookup_row1;
								}

							} // G 46

							forceLooprow1 = false;

							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_1__Struct Var = Var__tMap_1;// ###############################
								// ###############################
								// # Output tables

								customer_dim = null;

// # Output table : 'customer_dim'
								customer_dim_tmp.Employee_ID = customer.Employee_ID = row1.Employee_ID;
								customer_dim_tmp.Salary = row1.Salary;
								customer_dim_tmp.Job_Title = row1.Job_Title;
								customer_dim = customer_dim_tmp;
// ###############################

							} // end of Var scope

							rejectedInnerJoin_tMap_1 = false;

							tos_count_tMap_1++;

							/**
							 * [tMap_1 main ] stop
							 */

							/**
							 * [tMap_1 process_data_begin ] start
							 */

							currentComponent = "tMap_1";

							/**
							 * [tMap_1 process_data_begin ] stop
							 */
// Start of branch "customer_dim"
							if (customer_dim != null) {

								/**
								 * [tDBOutput_1 main ] start
								 */

								currentComponent = "tDBOutput_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "customer_dim"

									);
								}

								whetherReject_tDBOutput_1 = false;
								if (customer_dim.Employee_ID == null) {
									pstmt_tDBOutput_1.setNull(1, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(1, customer_dim.Employee_ID);
								}

								if (customer_dim.Salary == null) {
									pstmt_tDBOutput_1.setNull(2, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(2, customer_dim.Salary);
								}

								if (customer_dim.Job_Title == null) {
									pstmt_tDBOutput_1.setNull(3, java.sql.Types.VARCHAR);
								} else {
									pstmt_tDBOutput_1.setString(3, customer_dim.Job_Title);
								}

								pstmt_tDBOutput_1.addBatch();
								nb_line_tDBOutput_1++;

								batchSizeCounter_tDBOutput_1++;
								if (batchSize_tDBOutput_1 <= batchSizeCounter_tDBOutput_1) {
									try {
										int countSum_tDBOutput_1 = 0;
										for (int countEach_tDBOutput_1 : pstmt_tDBOutput_1.executeBatch()) {
											countSum_tDBOutput_1 += (countEach_tDBOutput_1 == java.sql.Statement.EXECUTE_FAILED
													? 0
													: 1);
										}
										rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;
										insertedCount_tDBOutput_1 += countSum_tDBOutput_1;
									} catch (java.sql.BatchUpdateException e) {
										globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());
										int countSum_tDBOutput_1 = 0;
										for (int countEach_tDBOutput_1 : e.getUpdateCounts()) {
											countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0
													: countEach_tDBOutput_1);
										}
										rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;
										insertedCount_tDBOutput_1 += countSum_tDBOutput_1;
										System.err.println(e.getMessage());
									}

									batchSizeCounter_tDBOutput_1 = 0;
								}
								commitCounter_tDBOutput_1++;

								if (commitEvery_tDBOutput_1 <= commitCounter_tDBOutput_1) {

									try {
										int countSum_tDBOutput_1 = 0;
										for (int countEach_tDBOutput_1 : pstmt_tDBOutput_1.executeBatch()) {
											countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0 : 1);
										}
										rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;
										insertedCount_tDBOutput_1 += countSum_tDBOutput_1;
									} catch (java.sql.BatchUpdateException e) {
										globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());
										int countSum_tDBOutput_1 = 0;
										for (int countEach_tDBOutput_1 : e.getUpdateCounts()) {
											countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0
													: countEach_tDBOutput_1);
										}
										rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;
										insertedCount_tDBOutput_1 += countSum_tDBOutput_1;
										System.err.println(e.getMessage());

									}
									if (rowsToCommitCount_tDBOutput_1 != 0) {
									}
									conn_tDBOutput_1.commit();
									if (rowsToCommitCount_tDBOutput_1 != 0) {
										rowsToCommitCount_tDBOutput_1 = 0;
									}
									commitCounter_tDBOutput_1 = 0;

								}

								tos_count_tDBOutput_1++;

								/**
								 * [tDBOutput_1 main ] stop
								 */

								/**
								 * [tDBOutput_1 process_data_begin ] start
								 */

								currentComponent = "tDBOutput_1";

								/**
								 * [tDBOutput_1 process_data_begin ] stop
								 */

								/**
								 * [tDBOutput_1 process_data_end ] start
								 */

								currentComponent = "tDBOutput_1";

								/**
								 * [tDBOutput_1 process_data_end ] stop
								 */

							} // End of branch "customer_dim"

						} // close loop of lookup 'row1' // G_TM_M_043

						/**
						 * [tMap_1 process_data_end ] start
						 */

						currentComponent = "tMap_1";

						/**
						 * [tMap_1 process_data_end ] stop
						 */

						/**
						 * [tDBInput_1 process_data_end ] start
						 */

						currentComponent = "tDBInput_1";

						/**
						 * [tDBInput_1 process_data_end ] stop
						 */

						/**
						 * [tDBInput_1 end ] start
						 */

						currentComponent = "tDBInput_1";

					}
				} finally {
					if (rs_tDBInput_1 != null) {
						rs_tDBInput_1.close();
					}
					if (stmt_tDBInput_1 != null) {
						stmt_tDBInput_1.close();
					}
					if (conn_tDBInput_1 != null && !conn_tDBInput_1.isClosed()) {

						conn_tDBInput_1.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

					}

				}

				globalMap.put("tDBInput_1_NB_LINE", nb_line_tDBInput_1);

				ok_Hash.put("tDBInput_1", true);
				end_Hash.put("tDBInput_1", System.currentTimeMillis());

				/**
				 * [tDBInput_1 end ] stop
				 */

				/**
				 * [tMap_1 end ] start
				 */

				currentComponent = "tMap_1";

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_row1 != null) {
					tHash_Lookup_row1.endGet();
				}
				globalMap.remove("tHash_Lookup_row1");

// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "customer");
				}

				ok_Hash.put("tMap_1", true);
				end_Hash.put("tMap_1", System.currentTimeMillis());

				/**
				 * [tMap_1 end ] stop
				 */

				/**
				 * [tDBOutput_1 end ] start
				 */

				currentComponent = "tDBOutput_1";

				try {
					if (batchSizeCounter_tDBOutput_1 != 0) {
						int countSum_tDBOutput_1 = 0;

						for (int countEach_tDBOutput_1 : pstmt_tDBOutput_1.executeBatch()) {
							countSum_tDBOutput_1 += (countEach_tDBOutput_1 == java.sql.Statement.EXECUTE_FAILED ? 0
									: 1);
						}
						rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;

						insertedCount_tDBOutput_1 += countSum_tDBOutput_1;

					}

				} catch (java.sql.BatchUpdateException e) {
					globalMap.put(currentComponent + "_ERROR_MESSAGE", e.getMessage());

					int countSum_tDBOutput_1 = 0;
					for (int countEach_tDBOutput_1 : e.getUpdateCounts()) {
						countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0 : countEach_tDBOutput_1);
					}
					rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;

					insertedCount_tDBOutput_1 += countSum_tDBOutput_1;

					System.err.println(e.getMessage());

				}
				batchSizeCounter_tDBOutput_1 = 0;

				if (pstmt_tDBOutput_1 != null) {

					pstmt_tDBOutput_1.close();
					resourceMap.remove("pstmt_tDBOutput_1");

				}
				resourceMap.put("statementClosed_tDBOutput_1", true);
				if (commitCounter_tDBOutput_1 > 0 && rowsToCommitCount_tDBOutput_1 != 0) {

				}
				conn_tDBOutput_1.commit();
				if (commitCounter_tDBOutput_1 > 0 && rowsToCommitCount_tDBOutput_1 != 0) {

					rowsToCommitCount_tDBOutput_1 = 0;
				}
				commitCounter_tDBOutput_1 = 0;

				conn_tDBOutput_1.close();

				resourceMap.put("finish_tDBOutput_1", true);

				nb_line_deleted_tDBOutput_1 = nb_line_deleted_tDBOutput_1 + deletedCount_tDBOutput_1;
				nb_line_update_tDBOutput_1 = nb_line_update_tDBOutput_1 + updatedCount_tDBOutput_1;
				nb_line_inserted_tDBOutput_1 = nb_line_inserted_tDBOutput_1 + insertedCount_tDBOutput_1;
				nb_line_rejected_tDBOutput_1 = nb_line_rejected_tDBOutput_1 + rejectedCount_tDBOutput_1;

				globalMap.put("tDBOutput_1_NB_LINE", nb_line_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_UPDATED", nb_line_update_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_DELETED", nb_line_deleted_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_1);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "customer_dim");
				}

				ok_Hash.put("tDBOutput_1", true);
				end_Hash.put("tDBOutput_1", System.currentTimeMillis());

				/**
				 * [tDBOutput_1 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tMap_1"
			globalMap.remove("tHash_Lookup_row1");

			try {

				/**
				 * [tDBInput_1 finally ] start
				 */

				currentComponent = "tDBInput_1";

				/**
				 * [tDBInput_1 finally ] stop
				 */

				/**
				 * [tMap_1 finally ] start
				 */

				currentComponent = "tMap_1";

				/**
				 * [tMap_1 finally ] stop
				 */

				/**
				 * [tDBOutput_1 finally ] start
				 */

				currentComponent = "tDBOutput_1";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_1") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_1 = null;
						if ((pstmtToClose_tDBOutput_1 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_1")) != null) {
							pstmtToClose_tDBOutput_1.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_1") == null) {
						java.sql.Connection ctn_tDBOutput_1 = null;
						if ((ctn_tDBOutput_1 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_1")) != null) {
							try {
								ctn_tDBOutput_1.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_1) {
								String errorMessage_tDBOutput_1 = "failed to close the connection in tDBOutput_1 :"
										+ sqlEx_tDBOutput_1.getMessage();
								System.err.println(errorMessage_tDBOutput_1);
							}
						}
					}
				}

				/**
				 * [tDBOutput_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_1_SUBPROCESS_STATE", 1);
	}

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_TP1_Jobs_customer_dim = new byte[0];
		static byte[] commonByteArray_TP1_Jobs_customer_dim = new byte[0];

		public String Employee_ID;

		public String getEmployee_ID() {
			return this.Employee_ID;
		}

		public String Start_Date;

		public String getStart_Date() {
			return this.Start_Date;
		}

		public String End_Date;

		public String getEnd_Date() {
			return this.End_Date;
		}

		public String Job_Title;

		public String getJob_Title() {
			return this.Job_Title;
		}

		public String Salary;

		public String getSalary() {
			return this.Salary;
		}

		public String Gender;

		public String getGender() {
			return this.Gender;
		}

		public java.util.Date Birth_Date;

		public java.util.Date getBirth_Date() {
			return this.Birth_Date;
		}

		public java.util.Date Emp_Hire_Date;

		public java.util.Date getEmp_Hire_Date() {
			return this.Emp_Hire_Date;
		}

		public java.util.Date Emp_Term_Date;

		public java.util.Date getEmp_Term_Date() {
			return this.Emp_Term_Date;
		}

		public String Manager_ID;

		public String getManager_ID() {
			return this.Manager_ID;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TP1_Jobs_customer_dim.length) {
					if (length < 1024 && commonByteArray_TP1_Jobs_customer_dim.length == 0) {
						commonByteArray_TP1_Jobs_customer_dim = new byte[1024];
					} else {
						commonByteArray_TP1_Jobs_customer_dim = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TP1_Jobs_customer_dim, 0, length);
				strReturn = new String(commonByteArray_TP1_Jobs_customer_dim, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TP1_Jobs_customer_dim.length) {
					if (length < 1024 && commonByteArray_TP1_Jobs_customer_dim.length == 0) {
						commonByteArray_TP1_Jobs_customer_dim = new byte[1024];
					} else {
						commonByteArray_TP1_Jobs_customer_dim = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TP1_Jobs_customer_dim, 0, length);
				strReturn = new String(commonByteArray_TP1_Jobs_customer_dim, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TP1_Jobs_customer_dim) {

				try {

					int length = 0;

					this.Employee_ID = readString(dis);

					this.Start_Date = readString(dis);

					this.End_Date = readString(dis);

					this.Job_Title = readString(dis);

					this.Salary = readString(dis);

					this.Gender = readString(dis);

					this.Birth_Date = readDate(dis);

					this.Emp_Hire_Date = readDate(dis);

					this.Emp_Term_Date = readDate(dis);

					this.Manager_ID = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TP1_Jobs_customer_dim) {

				try {

					int length = 0;

					this.Employee_ID = readString(dis);

					this.Start_Date = readString(dis);

					this.End_Date = readString(dis);

					this.Job_Title = readString(dis);

					this.Salary = readString(dis);

					this.Gender = readString(dis);

					this.Birth_Date = readDate(dis);

					this.Emp_Hire_Date = readDate(dis);

					this.Emp_Term_Date = readDate(dis);

					this.Manager_ID = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Employee_ID, dos);

				// String

				writeString(this.Start_Date, dos);

				// String

				writeString(this.End_Date, dos);

				// String

				writeString(this.Job_Title, dos);

				// String

				writeString(this.Salary, dos);

				// String

				writeString(this.Gender, dos);

				// java.util.Date

				writeDate(this.Birth_Date, dos);

				// java.util.Date

				writeDate(this.Emp_Hire_Date, dos);

				// java.util.Date

				writeDate(this.Emp_Term_Date, dos);

				// String

				writeString(this.Manager_ID, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Employee_ID, dos);

				// String

				writeString(this.Start_Date, dos);

				// String

				writeString(this.End_Date, dos);

				// String

				writeString(this.Job_Title, dos);

				// String

				writeString(this.Salary, dos);

				// String

				writeString(this.Gender, dos);

				// java.util.Date

				writeDate(this.Birth_Date, dos);

				// java.util.Date

				writeDate(this.Emp_Hire_Date, dos);

				// java.util.Date

				writeDate(this.Emp_Term_Date, dos);

				// String

				writeString(this.Manager_ID, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Employee_ID=" + Employee_ID);
			sb.append(",Start_Date=" + Start_Date);
			sb.append(",End_Date=" + End_Date);
			sb.append(",Job_Title=" + Job_Title);
			sb.append(",Salary=" + Salary);
			sb.append(",Gender=" + Gender);
			sb.append(",Birth_Date=" + String.valueOf(Birth_Date));
			sb.append(",Emp_Hire_Date=" + String.valueOf(Emp_Hire_Date));
			sb.append(",Emp_Term_Date=" + String.valueOf(Emp_Term_Date));
			sb.append(",Manager_ID=" + Manager_ID);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputExcel_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row1Struct row1 = new row1Struct();

				/**
				 * [tAdvancedHash_row1 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row1", false);
				start_Hash.put("tAdvancedHash_row1", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row1");
				}

				int tos_count_tAdvancedHash_row1 = 0;

				// connection name:row1
				// source node:tFileInputExcel_1 - inputs:(after_tDBInput_1) outputs:(row1,row1)
				// | target node:tAdvancedHash_row1 - inputs:(row1) outputs:()
				// linked node: tMap_1 - inputs:(customer,row1) outputs:(customer_dim)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row1 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.ALL_ROWS;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row1Struct> tHash_Lookup_row1 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row1Struct>getLookup(matchingModeEnum_row1);

				globalMap.put("tHash_Lookup_row1", tHash_Lookup_row1);

				/**
				 * [tAdvancedHash_row1 begin ] stop
				 */

				/**
				 * [tFileInputExcel_1 begin ] start
				 */

				ok_Hash.put("tFileInputExcel_1", false);
				start_Hash.put("tFileInputExcel_1", System.currentTimeMillis());

				currentComponent = "tFileInputExcel_1";

				int tos_count_tFileInputExcel_1 = 0;

				class RegexUtil_tFileInputExcel_1 {

					public java.util.List<jxl.Sheet> getSheets(jxl.Workbook workbook, String oneSheetName,
							boolean useRegex) {

						java.util.List<jxl.Sheet> list = new java.util.ArrayList<jxl.Sheet>();

						if (useRegex) {// this part process the regex issue

							jxl.Sheet[] sheets = workbook.getSheets();
							java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(oneSheetName);
							for (int i = 0; i < sheets.length; i++) {
								String sheetName = sheets[i].getName();
								java.util.regex.Matcher matcher = pattern.matcher(sheetName);
								if (matcher.matches()) {
									jxl.Sheet sheet = workbook.getSheet(sheetName);
									if (sheet != null) {
										list.add(sheet);
									}
								}
							}

						} else {
							jxl.Sheet sheet = workbook.getSheet(oneSheetName);
							if (sheet != null) {
								list.add(sheet);
							}

						}

						return list;
					}

					public java.util.List<jxl.Sheet> getSheets(jxl.Workbook workbook, int index, boolean useRegex) {
						java.util.List<jxl.Sheet> list = new java.util.ArrayList<jxl.Sheet>();
						jxl.Sheet sheet = workbook.getSheet(index);
						if (sheet != null) {
							list.add(sheet);
						}
						return list;
					}

				}

				RegexUtil_tFileInputExcel_1 regexUtil_tFileInputExcel_1 = new RegexUtil_tFileInputExcel_1();
				final jxl.WorkbookSettings workbookSettings_tFileInputExcel_1 = new jxl.WorkbookSettings();
				workbookSettings_tFileInputExcel_1.setDrawingsDisabled(true);
				workbookSettings_tFileInputExcel_1.setEncoding("UTF-8");

				Object source_tFileInputExcel_1 = "C:/Users/talel/Desktop/Talend/projet DWH/staff.xls";
				final jxl.Workbook workbook_tFileInputExcel_1;

				java.io.InputStream toClose_tFileInputExcel_1 = null;
				java.io.BufferedInputStream buffIStreamtFileInputExcel_1 = null;
				try {
					if (source_tFileInputExcel_1 instanceof java.io.InputStream) {
						toClose_tFileInputExcel_1 = (java.io.InputStream) source_tFileInputExcel_1;
						buffIStreamtFileInputExcel_1 = new java.io.BufferedInputStream(toClose_tFileInputExcel_1);
						workbook_tFileInputExcel_1 = jxl.Workbook.getWorkbook(buffIStreamtFileInputExcel_1,
								workbookSettings_tFileInputExcel_1);
					} else if (source_tFileInputExcel_1 instanceof String) {
						toClose_tFileInputExcel_1 = new java.io.FileInputStream(source_tFileInputExcel_1.toString());
						buffIStreamtFileInputExcel_1 = new java.io.BufferedInputStream(toClose_tFileInputExcel_1);
						workbook_tFileInputExcel_1 = jxl.Workbook.getWorkbook(buffIStreamtFileInputExcel_1,
								workbookSettings_tFileInputExcel_1);
					} else {
						workbook_tFileInputExcel_1 = null;
						throw new java.lang.Exception(
								"The data source should be specified as Inputstream or File Path!");
					}
				} finally {
					try {
						if (buffIStreamtFileInputExcel_1 != null) {
							buffIStreamtFileInputExcel_1.close();
						}
					} catch (Exception e) {
						globalMap.put("tFileInputExcel_1_ERROR_MESSAGE", e.getMessage());
					}
				}
				try {
					java.util.List<jxl.Sheet> sheetList_tFileInputExcel_1 = java.util.Arrays.<jxl.Sheet>asList(
							workbook_tFileInputExcel_1.getSheets());
					if (sheetList_tFileInputExcel_1.size() <= 0) {
						throw new RuntimeException("Special sheets not exist!");
					}

					java.util.List<jxl.Sheet> sheet_FilterNullList_tFileInputExcel_1 = new java.util.ArrayList<jxl.Sheet>();
					for (jxl.Sheet sheet_FilterNull_tFileInputExcel_1 : sheetList_tFileInputExcel_1) {
						if (sheet_FilterNull_tFileInputExcel_1.getRows() > 0) {
							sheet_FilterNullList_tFileInputExcel_1.add(sheet_FilterNull_tFileInputExcel_1);
						}
					}
					sheetList_tFileInputExcel_1 = sheet_FilterNullList_tFileInputExcel_1;
					if (sheetList_tFileInputExcel_1.size() > 0) {
						int nb_line_tFileInputExcel_1 = 0;

						int begin_line_tFileInputExcel_1 = 1;

						int footer_input_tFileInputExcel_1 = 0;

						int end_line_tFileInputExcel_1 = 0;
						for (jxl.Sheet sheet_tFileInputExcel_1 : sheetList_tFileInputExcel_1) {
							end_line_tFileInputExcel_1 += sheet_tFileInputExcel_1.getRows();
						}
						end_line_tFileInputExcel_1 -= footer_input_tFileInputExcel_1;
						int limit_tFileInputExcel_1 = -1;
						int start_column_tFileInputExcel_1 = 1 - 1;
						int end_column_tFileInputExcel_1 = sheetList_tFileInputExcel_1.get(0).getColumns();
						jxl.Cell[] row_tFileInputExcel_1 = null;
						jxl.Sheet sheet_tFileInputExcel_1 = sheetList_tFileInputExcel_1.get(0);
						int rowCount_tFileInputExcel_1 = 0;
						int sheetIndex_tFileInputExcel_1 = 0;
						int currentRows_tFileInputExcel_1 = sheetList_tFileInputExcel_1.get(0).getRows();

						// for the number format
						java.text.DecimalFormat df_tFileInputExcel_1 = new java.text.DecimalFormat(
								"#.####################################");
						char separatorChar_tFileInputExcel_1 = df_tFileInputExcel_1.getDecimalFormatSymbols()
								.getDecimalSeparator();

						for (int i_tFileInputExcel_1 = begin_line_tFileInputExcel_1; i_tFileInputExcel_1 < end_line_tFileInputExcel_1; i_tFileInputExcel_1++) {

							int emptyColumnCount_tFileInputExcel_1 = 0;

							if (limit_tFileInputExcel_1 != -1 && nb_line_tFileInputExcel_1 >= limit_tFileInputExcel_1) {
								break;
							}

							while (i_tFileInputExcel_1 >= rowCount_tFileInputExcel_1 + currentRows_tFileInputExcel_1) {
								rowCount_tFileInputExcel_1 += currentRows_tFileInputExcel_1;
								sheet_tFileInputExcel_1 = sheetList_tFileInputExcel_1
										.get(++sheetIndex_tFileInputExcel_1);
								currentRows_tFileInputExcel_1 = sheet_tFileInputExcel_1.getRows();
							}
							if (rowCount_tFileInputExcel_1 <= i_tFileInputExcel_1) {
								row_tFileInputExcel_1 = sheet_tFileInputExcel_1
										.getRow(i_tFileInputExcel_1 - rowCount_tFileInputExcel_1);
							}
							globalMap.put("tFileInputExcel_1_CURRENT_SHEET", sheet_tFileInputExcel_1.getName());
							row1 = null;
							row1 = null;
							int tempRowLength_tFileInputExcel_1 = 10;

							int columnIndex_tFileInputExcel_1 = 0;

//
//end%>

							String[] temp_row_tFileInputExcel_1 = new String[tempRowLength_tFileInputExcel_1];
							int actual_end_column_tFileInputExcel_1 = end_column_tFileInputExcel_1 > row_tFileInputExcel_1.length
									? row_tFileInputExcel_1.length
									: end_column_tFileInputExcel_1;

							java.util.TimeZone zone_tFileInputExcel_1 = java.util.TimeZone.getTimeZone("GMT");
							java.text.SimpleDateFormat sdf_tFileInputExcel_1 = new java.text.SimpleDateFormat(
									"dd-MM-yyyy");
							sdf_tFileInputExcel_1.setTimeZone(zone_tFileInputExcel_1);

							for (int i = 0; i < tempRowLength_tFileInputExcel_1; i++) {

								if (i + start_column_tFileInputExcel_1 < actual_end_column_tFileInputExcel_1) {

									jxl.Cell cell_tFileInputExcel_1 = row_tFileInputExcel_1[i
											+ start_column_tFileInputExcel_1];
									temp_row_tFileInputExcel_1[i] = cell_tFileInputExcel_1.getContents();

								} else {
									temp_row_tFileInputExcel_1[i] = "";
								}
							}

							boolean whetherReject_tFileInputExcel_1 = false;
							row1 = new row1Struct();
							int curColNum_tFileInputExcel_1 = -1;
							String curColName_tFileInputExcel_1 = "";
							try {
								columnIndex_tFileInputExcel_1 = 0;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Employee_ID";
									row1.Employee_ID = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Employee_ID = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 1;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Start_Date";
									row1.Start_Date = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Start_Date = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 2;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "End_Date";
									row1.End_Date = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.End_Date = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 3;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Job_Title";
									row1.Job_Title = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Job_Title = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 4;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Salary";
									row1.Salary = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Salary = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 5;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Gender";
									row1.Gender = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Gender = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 6;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Birth_Date";
									if (6 < actual_end_column_tFileInputExcel_1) {
										try {
											java.util.Date dateGMT_tFileInputExcel_1 = ((jxl.DateCell) row_tFileInputExcel_1[columnIndex_tFileInputExcel_1
													+ start_column_tFileInputExcel_1]).getDate();
											row1.Birth_Date = new java.util.Date(dateGMT_tFileInputExcel_1.getTime()
													- java.util.TimeZone.getDefault()
															.getOffset(dateGMT_tFileInputExcel_1.getTime()));
										} catch (java.lang.Exception e) {
											globalMap.put("tFileInputExcel_1_ERROR_MESSAGE", e.getMessage());

											throw new RuntimeException("The cell format is not Date in ( Row. "
													+ (nb_line_tFileInputExcel_1 + 1) + " and ColumnNum. "
													+ curColNum_tFileInputExcel_1 + " )");
										}
									}
								} else {
									row1.Birth_Date = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 7;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Emp_Hire_Date";
									if (7 < actual_end_column_tFileInputExcel_1) {
										try {
											java.util.Date dateGMT_tFileInputExcel_1 = ((jxl.DateCell) row_tFileInputExcel_1[columnIndex_tFileInputExcel_1
													+ start_column_tFileInputExcel_1]).getDate();
											row1.Emp_Hire_Date = new java.util.Date(dateGMT_tFileInputExcel_1.getTime()
													- java.util.TimeZone.getDefault()
															.getOffset(dateGMT_tFileInputExcel_1.getTime()));
										} catch (java.lang.Exception e) {
											globalMap.put("tFileInputExcel_1_ERROR_MESSAGE", e.getMessage());

											throw new RuntimeException("The cell format is not Date in ( Row. "
													+ (nb_line_tFileInputExcel_1 + 1) + " and ColumnNum. "
													+ curColNum_tFileInputExcel_1 + " )");
										}
									}
								} else {
									row1.Emp_Hire_Date = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 8;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Emp_Term_Date";
									if (8 < actual_end_column_tFileInputExcel_1) {
										try {
											java.util.Date dateGMT_tFileInputExcel_1 = ((jxl.DateCell) row_tFileInputExcel_1[columnIndex_tFileInputExcel_1
													+ start_column_tFileInputExcel_1]).getDate();
											row1.Emp_Term_Date = new java.util.Date(dateGMT_tFileInputExcel_1.getTime()
													- java.util.TimeZone.getDefault()
															.getOffset(dateGMT_tFileInputExcel_1.getTime()));
										} catch (java.lang.Exception e) {
											globalMap.put("tFileInputExcel_1_ERROR_MESSAGE", e.getMessage());

											throw new RuntimeException("The cell format is not Date in ( Row. "
													+ (nb_line_tFileInputExcel_1 + 1) + " and ColumnNum. "
													+ curColNum_tFileInputExcel_1 + " )");
										}
									}
								} else {
									row1.Emp_Term_Date = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 9;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Manager_ID";
									row1.Manager_ID = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Manager_ID = null;
									emptyColumnCount_tFileInputExcel_1++;
								}

								nb_line_tFileInputExcel_1++;

							} catch (java.lang.Exception e) {
								globalMap.put("tFileInputExcel_1_ERROR_MESSAGE", e.getMessage());
								whetherReject_tFileInputExcel_1 = true;
								System.err.println(e.getMessage());
								row1 = null;
							}

							/**
							 * [tFileInputExcel_1 begin ] stop
							 */

							/**
							 * [tFileInputExcel_1 main ] start
							 */

							currentComponent = "tFileInputExcel_1";

							tos_count_tFileInputExcel_1++;

							/**
							 * [tFileInputExcel_1 main ] stop
							 */

							/**
							 * [tFileInputExcel_1 process_data_begin ] start
							 */

							currentComponent = "tFileInputExcel_1";

							/**
							 * [tFileInputExcel_1 process_data_begin ] stop
							 */
// Start of branch "row1"
							if (row1 != null) {

								/**
								 * [tAdvancedHash_row1 main ] start
								 */

								currentComponent = "tAdvancedHash_row1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row1"

									);
								}

								row1Struct row1_HashRow = new row1Struct();

								row1_HashRow.Employee_ID = row1.Employee_ID;

								row1_HashRow.Start_Date = row1.Start_Date;

								row1_HashRow.End_Date = row1.End_Date;

								row1_HashRow.Job_Title = row1.Job_Title;

								row1_HashRow.Salary = row1.Salary;

								row1_HashRow.Gender = row1.Gender;

								row1_HashRow.Birth_Date = row1.Birth_Date;

								row1_HashRow.Emp_Hire_Date = row1.Emp_Hire_Date;

								row1_HashRow.Emp_Term_Date = row1.Emp_Term_Date;

								row1_HashRow.Manager_ID = row1.Manager_ID;

								tHash_Lookup_row1.put(row1_HashRow);

								tos_count_tAdvancedHash_row1++;

								/**
								 * [tAdvancedHash_row1 main ] stop
								 */

								/**
								 * [tAdvancedHash_row1 process_data_begin ] start
								 */

								currentComponent = "tAdvancedHash_row1";

								/**
								 * [tAdvancedHash_row1 process_data_begin ] stop
								 */

								/**
								 * [tAdvancedHash_row1 process_data_end ] start
								 */

								currentComponent = "tAdvancedHash_row1";

								/**
								 * [tAdvancedHash_row1 process_data_end ] stop
								 */

							} // End of branch "row1"

							/**
							 * [tFileInputExcel_1 process_data_end ] start
							 */

							currentComponent = "tFileInputExcel_1";

							/**
							 * [tFileInputExcel_1 process_data_end ] stop
							 */

							/**
							 * [tFileInputExcel_1 end ] start
							 */

							currentComponent = "tFileInputExcel_1";

						}

						globalMap.put("tFileInputExcel_1_NB_LINE", nb_line_tFileInputExcel_1);

					}

				} finally {

					if (!(source_tFileInputExcel_1 instanceof java.io.InputStream)) {
						workbook_tFileInputExcel_1.close();
					}

				}

				ok_Hash.put("tFileInputExcel_1", true);
				end_Hash.put("tFileInputExcel_1", System.currentTimeMillis());

				/**
				 * [tFileInputExcel_1 end ] stop
				 */

				/**
				 * [tAdvancedHash_row1 end ] start
				 */

				currentComponent = "tAdvancedHash_row1";

				tHash_Lookup_row1.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row1");
				}

				ok_Hash.put("tAdvancedHash_row1", true);
				end_Hash.put("tAdvancedHash_row1", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row1 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputExcel_1 finally ] start
				 */

				currentComponent = "tFileInputExcel_1";

				/**
				 * [tFileInputExcel_1 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row1 finally ] start
				 */

				currentComponent = "tAdvancedHash_row1";

				/**
				 * [tAdvancedHash_row1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Default";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean enableLogStash;

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	protected PropertiesWithType context_param = new PropertiesWithType();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	public static void main(String[] args) {
		final Jobs_customer_dim Jobs_customer_dimClass = new Jobs_customer_dim();

		int exitCode = Jobs_customer_dimClass.runJobInTOS(args);

		System.exit(exitCode);
	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}
		enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		if (rootPid == null) {
			rootPid = pid;
		}
		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}

		if (portStats != null) {
			// portStats = -1; //for testing
			if (portStats < 0 || portStats > 65535) {
				// issue:10869, the portStats is invalid, so this client socket can't open
				System.err.println("The statistics socket port " + portStats + " is invalid.");
				execStat = false;
			}
		} else {
			execStat = false;
		}
		boolean inOSGi = routines.system.BundleUtils.inOSGi();

		if (inOSGi) {
			java.util.Dictionary<String, Object> jobProperties = routines.system.BundleUtils.getJobProperties(jobName);

			if (jobProperties != null && jobProperties.get("context") != null) {
				contextStr = (String) jobProperties.get("context");
			}
		}

		try {
			// call job/subjob with an existing context, like: --context=production. if
			// without this parameter, there will use the default context instead.
			java.io.InputStream inContext = Jobs_customer_dim.class.getClassLoader()
					.getResourceAsStream("tp1/jobs_customer_dim_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = Jobs_customer_dim.class.getClassLoader()
						.getResourceAsStream("config/contexts/" + contextStr + ".properties");
			}
			if (inContext != null) {
				try {
					// defaultProps is in order to keep the original context value
					if (context != null && context.isEmpty()) {
						defaultProps.load(inContext);
						context = new ContextProperties(defaultProps);
					}
				} finally {
					inContext.close();
				}
			} else if (!isDefaultContext) {
				// print info and job continue to run, for case: context_param is not empty.
				System.err.println("Could not find the context " + contextStr);
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
				// set types for params from parentJobs
				for (Object key : context_param.keySet()) {
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
			}
			class ContextProcessing {
				private void processContext_0() {
				}

				public void processAllContext() {
					processContext_0();
				}
			}

			new ContextProcessing().processAllContext();
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "",
				"", "", "", "", resumeUtil.convertToJsonText(context, parametersToEncrypt));

		if (execStat) {
			try {
				runStat.openSocket(!isChildJob);
				runStat.setAllPID(rootPid, fatherPid, pid, jobName);
				runStat.startThreadStat(clientHost, portStats);
				runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
			} catch (java.io.IOException ioException) {
				ioException.printStackTrace();
			}
		}

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tDBInput_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tDBInput_1) {
			globalMap.put("tDBInput_1_SUBPROCESS_STATE", -1);

			e_tDBInput_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println(
					(endUsedMemory - startUsedMemory) + " bytes memory increase when running : Jobs_customer_dim");
		}

		if (execStat) {
			runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
			runStat.stopThreadStat();
		}
		int returnCode = 0;

		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "",
				"" + returnCode, "", "", "");

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_type")) {
			String keyValue = arg.substring(15);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.setContextType(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1));
				}

			}

		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {// for trunjob call
			final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
	}

	private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" }, { "\\'", "\'" }, { "\\r", "\r" },
			{ "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the
			// result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 102828 characters generated by Talend Open Studio for Data Integration on the
 * 17 décembre 2023 à 20:34:25 GMT+01:00
 ************************************************************************************************/