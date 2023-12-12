package org.finalProject;

import org.finalProject.helpers.ItemHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ItemTest<T> {

	ItemHelper itemHelper;

	public static Stream<Arguments> testValues() {
		return Stream.of(
				Arguments.of("", ""),
				Arguments.of("TEST", "test"),
				Arguments.of("1A2B3C", "1a2b3c"),
				Arguments.of("aaaaaVeryyyyyLoooooongIiiiiid", "aaaaaveryyyyyloooooongiiiiiid"),
				Arguments.of(null, NullPointerException.class)

		);
	}

	@ParameterizedTest
	@MethodSource("testValues")
	@DisplayName("constructor Test")
	void testConstructor(T input, T expected) {

		if (input == null) {
			assertThrows((Class<? extends Throwable>) expected, () -> new ItemHelper((String) input));
		} else {
			assertEquals((String) expected, new ItemHelper((String) input).getId());
		}
	}

	@ParameterizedTest
	@MethodSource("testValues")
	@DisplayName("setId() Test")
	void testSetId(T input, T expected) {
		itemHelper = new ItemHelper("");

		if (input == null) {

			assertThrows((Class<? extends Throwable>) expected, () -> itemHelper.setId((String) input));

		} else {
			itemHelper.setId((String) input);
			assertEquals((String) expected, new ItemHelper((String) input).getId());

		}
	}

	@ParameterizedTest
	@MethodSource("testValues")
	@DisplayName("toString() Test")
	void testToString(T input, T expected) {

		if (input != null) {
			assertEquals((String) expected, new ItemHelper((String) input).toString());
		} else {
			assertThrows((Class<? extends Throwable>) expected, () -> itemHelper.setId((String) input));
		}
	}
}
