package com.thoughtworks.args;

import com.thoughtworks.args.exceptions.SyntaxException;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SchemaTest {

    @Test
    public void should_get_int_default_value_0_when_int_flag_exist_and_value_is_empty() throws SyntaxException {
        // given

        Schema schema = new Schema("l:int");
        List<Arg> inputArgs = Arrays.asList(new Arg("l", ""));

        // when
        Object value = schema.convert(inputArgs.get(0));

        // then
        assertEquals(0, value);
    }

    @Test
    public void should_get_string_default_value_empty_when_string_flag_exist_and_value_is_empty() throws SyntaxException {
        // given

        Schema schema = new Schema("p:string");
        List<Arg> inputArgs = Arrays.asList(new Arg("p", ""));

        // when
        Object value = schema.convert(inputArgs.get(0));

        // then
        assertEquals("", value);
    }

    @Test
    public void shoule_get_boolean_default_value_false_when_boolean_flag_exist_and_value_is_empty() throws SyntaxException {
        // given

        Schema schema = new Schema("f:boolean");
        List<Arg> inputArgs = Arrays.asList(new Arg("f", ""));

        // when
        Object value = schema.convert(inputArgs.get(0));

        // then
        assertEquals(false, value);
    }

    @Test
    public void shoule_get_multiple_default_value_when_diff_type_flags_exist_and_values_are_empty() throws SyntaxException {
        // given

        Schema schema = new Schema("f:boolean p:string");
        List<Arg> inputArgs = Arrays.asList(new Arg("f", ""), new Arg("p", ""));

        // when
        Object value1 = schema.convert(inputArgs.get(0));
        Object value2 = schema.convert(inputArgs.get(1));

        // then
        assertEquals(false, value1);
        assertEquals("", value2);
    }

    @Test
    public void shoule_get_input_int_value_when_int_flag_exist_and_value_is_not_empty() throws SyntaxException {
        // given

        Schema schema = new Schema("l:int");
        List<Arg> inputArgs = Arrays.asList(new Arg("l", "10"));

        // when
        Object value = schema.convert(inputArgs.get(0));

        // then
        assertEquals(10, value);
    }

    @Test
    public void shoule_get_input_string_value_when_string_flag_exist_and_value_is_not_empty() throws SyntaxException {
        // given

        Schema schema = new Schema("f:string");
        List<Arg> inputArgs = Arrays.asList(new Arg("f", "/usr/path"));

        // when
        Object value = schema.convert(inputArgs.get(0));

        // then
        assertEquals("/usr/path", value);
    }

    @Test
    public void shoule_get_input_boolean_value_when_boolean_flag_exist_and_value_is_not_empty() throws SyntaxException {
        // given

        Schema schema = new Schema("f:boolean");
        List<Arg> inputArgs = Arrays.asList(new Arg("f", "true"));

        // when
        Object value = schema.convert(inputArgs.get(0));

        // then
        assertEquals(true, value);
    }

    @Test(expected = SyntaxException.class)
    public void scan_should_return_fault_when_flag_name_not_match_any() throws SyntaxException {
    	// given
    	Schema schema = new Schema("f:boolean");
    	List<Arg> inputArgs = Arrays.asList(new Arg("x", ""));
    	// when
    	Object value = schema.convert(inputArgs.get(0));
    }
}
