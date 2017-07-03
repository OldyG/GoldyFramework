/**
 * FileName : {@link EnumPropertyRunner}.java
 * Created : 2017. 4. 10.
 * Author : jeong
 * Summary :
 * Copyright (C) 2017 Goldy Project. All rights reserved.
 * 이 문서의 모든 저작권 및 지적 재산권은 Goldy Project에게 있습니다.
 * 이 문서의 어떠한 부분도 허가 없이 복제 또는 수정 하거나, 전송할 수 없습니다.
 */
package com.goldyframework.enumproperty;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.goldyframework.inspection.ObjectInspection;

/**
 * Enum 프로퍼티 관리도구 실행도구
 *
 * @author 2017. 6. 14. 오후 8:41:42 jeong
 * @deprecated (개발중인 코드)
 */
@Deprecated
public class EnumPropertyRunner {

	/**
	 * slf4j Logger
	 *
	 * @author jeong
	 * @since 2017. 5. 22. 오후 9:20:02
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(EnumPropertyRunner.class);

	/**
	 * 실제 프로퍼티
	 */
	@SuppressWarnings("unused")
	private final File realProperties;

	/**
	 * 프로퍼티
	 */
	private final Properties classProperties = new Properties();

	/**
	 * {@link Reflections}
	 */
	private final Reflections reflections;

	/**
	 * 컨텐츠 맵
	 */
	private final Map<String, String> contentMap = new HashMap<>();

	/**
	 * {@link EnumPropertyRunner} 클래스의 새 인스턴스를 초기화 합니다.
	 *
	 * @author 2017. 6. 14. 오후 8:42:15 jeong
	 * @param propertiesPath
	 *            프로퍼티
	 * @param mainClass
	 *            실행 클래스
	 * @throws IOException
	 *             File클래스를 통해 리소스를 읽어드리는 과정에 발생 할 수 있습니다.
	 */
	public EnumPropertyRunner(final String propertiesPath, final Class<?> mainClass) throws IOException {
		ObjectInspection.checkNull(propertiesPath);
		ObjectInspection.checkNull(mainClass);
		final String propertieName = propertiesPath + ".properties"; //$NON-NLS-1$
		this.realProperties = new File("src/main/resources/", propertieName); //$NON-NLS-1$
		final InputStream inputStream = EnumPropertyRunner.class.getClassLoader().getResourceAsStream(propertieName);
		this.classProperties.load(inputStream);

		inputStream.close();
		this.reflections = new Reflections(mainClass.getPackage().getName());
	}

	/**
	 * 맵에 이름을 추가한다.
	 *
	 * @author 2017. 6. 18. 오후 1:00:07 jeong
	 * @param name
	 *            이름
	 * @param simpleName
	 *            간소화된 이름
	 */
	private void addMap(final String name, final String simpleName) {

		this.contentMap.put(name, simpleName);
	}

	/**
	 * 분석을 실행한다.
	 *
	 * @author 2017. 6. 18. 오후 1:00:19 jeong
	 */
	private void analyse() {

		final Collection<Class<?>> enumPropertyClasses = this.reflections.getTypesAnnotatedWith(EnumProperty.class);
		for (final Class<?> enumPropertyClasse : enumPropertyClasses) {

			if (enumPropertyClasse.isEnum() == false) {
				final String message = MessageFormat.format("{0}은 Enum 클래스가 아니므로 제외합니다.", //$NON-NLS-1$
					enumPropertyClasse.getName());
				LOGGER.debug(message);
				continue;
			}

			final String name = enumPropertyClasse.getName();
			this.addMap(name, enumPropertyClasse.getSimpleName());

			final Field[] fields = enumPropertyClasse.getFields();
			for (final Field field : fields) {
				this.addMap(name + '.' + field.getName(), field.getName());
			}
		}
	}

	/**
	 * Enum 프로퍼티 분석을 실행합니다.
	 *
	 * @author 2017. 6. 14. 오후 9:04:16 jeong
	 */
	public void run() {

		this.analyse();
		this.write();
	}

	/**
	 * 작성 작업을 실행한다.
	 *
	 * @author 2017. 6. 18. 오후 1:00:25 jeong
	 */
	private void write() {

		final Set<Entry<String, String>> entrySet = this.contentMap.entrySet();
		final StringBuilder builder = new StringBuilder();
		for (final Entry<String, String> entry : entrySet) {
			this.classProperties.setProperty(entry.getKey(), entry.getValue());
			builder.append(MessageFormat.format("{0} = {1}\n", entry.getKey(), entry.getValue())); //$NON-NLS-1$
		}
		final String message = builder.toString();
		LOGGER.info(message);
	}
}
