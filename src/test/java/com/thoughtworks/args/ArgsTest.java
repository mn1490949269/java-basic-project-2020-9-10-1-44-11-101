package com.thoughtworks.args;

import com.thoughtworks.args.exceptions.LexicalException;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ArgsTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void scan_should_return_args_list_with_one_arg_given_input_has_one_flag() throws LexicalException {

        // given
        Args parser = new Args();
        String paramStr = "-l";

        // when
        List<Arg> scanParams = parser.scan(paramStr);

        // then
        assertEquals(1, scanParams.size());
        Assert.assertEquals("l", scanParams.get(0).getFlag());
        Assert.assertEquals("", scanParams.get(0).getValue());

    }

    @Test
    public void scan_should_return_args_list_with_one_arg_given_input_has_one_flag_but_has_spaces_after() throws LexicalException {
        // given
    	Args parser = new Args();
        String paramStr = "-l    ";

        // when
        List<Arg> scanParams = parser.scan(paramStr);

        // then
        assertEquals(1, scanParams.size());
        Assert.assertEquals("l", scanParams.get(0).getFlag());
        Assert.assertEquals("", scanParams.get(0).getValue());

    }

    @Test
    public void scan_should_return_args_list_with_one_arg_given_input_has_one_flag_but_has_spaces_before() throws LexicalException {
        // given
    	Args parser = new Args();
        String paramStr = "    -l";

        // when
        List<Arg> scanParams = parser.scan(paramStr);

        // then
        assertEquals(1, scanParams.size());
        Assert.assertEquals("l", scanParams.get(0).getFlag());
        Assert.assertEquals("", scanParams.get(0).getValue());

    }

    @Test
    public void scan_should_return_args_list_with_two_arg_given_input_has_two_flag_split_by_one_space() throws LexicalException {
        // given
    	Args parser = new Args();
        String paramStr = "-l -p";

        // when
        List<Arg> scanParams = parser.scan(paramStr);

        // then
        assertEquals(2, scanParams.size());
        Assert.assertEquals("l", scanParams.get(0).getFlag());
        Assert.assertEquals("", scanParams.get(0).getValue());
        Assert.assertEquals("p", scanParams.get(1).getFlag());
        Assert.assertEquals("", scanParams.get(1).getValue());

    }

    @Test
    public void scan_should_return_args_list_with_two_arg_given_input_has_two_flag_split_by_spaces() throws LexicalException {
        // given
    	Args parser = new Args();
        String paramStr = "-l     -p";

        // when
        List<Arg> scanParams = parser.scan(paramStr);

        // then
        assertEquals(2, scanParams.size());
        Assert.assertEquals("l", scanParams.get(0).getFlag());
        Assert.assertEquals("", scanParams.get(0).getValue());
        Assert.assertEquals("p", scanParams.get(1).getFlag());
        Assert.assertEquals("", scanParams.get(1).getValue());

    }

    @Test
    public void scan_should_return_args_list_with_two_arg_given_input_has_two_flag_but_has_spaces_before() throws LexicalException {
        // given
    	Args parser = new Args();
        String paramStr = "     -l -p";

        // when
        List<Arg> scanParams = parser.scan(paramStr);

        // then
        assertEquals(2, scanParams.size());
        Assert.assertEquals("l", scanParams.get(0).getFlag());
        Assert.assertEquals("", scanParams.get(0).getValue());
        Assert.assertEquals("p", scanParams.get(1).getFlag());
        Assert.assertEquals("", scanParams.get(1).getValue());

    }

    @Test
    public void scan_should_return_args_list_with_two_arg_given_input_has_two_flag_but_has_spaces_after() throws LexicalException {
        // given
    	Args parser = new Args();
        String paramStr = "-l -p    ";

        // when
        List<Arg> scanParams = parser.scan(paramStr);

        // then
        assertEquals(2, scanParams.size());
        Assert.assertEquals("l", scanParams.get(0).getFlag());
        Assert.assertEquals("", scanParams.get(0).getValue());
        Assert.assertEquals("p", scanParams.get(1).getFlag());
        Assert.assertEquals("", scanParams.get(1).getValue());

    }

    @Test
    public void scan_should_return_args_list_with_one_arg_having_value_given_input_has_one_flag_with_value() throws LexicalException {
        // todo: first pass this test will fail all previous test - test point

        // given
        Args parser = new Args();
        String paramStr = "-l value";

        // when
        List<Arg> scanParams = parser.scan(paramStr);

        // then
        assertEquals(1, scanParams.size());
        Assert.assertEquals("l", scanParams.get(0).getFlag());
        Assert.assertEquals("value", scanParams.get(0).getValue());

    }

    @Test
    public void scan_should_return_args_list_with_one_arg_having_value_given_input_has_two_flags_one_with_value() throws LexicalException {

        // given
        Args parser = new Args();
        String paramStr = "-l value -p";

        // when
        List<Arg> scanParams = parser.scan(paramStr);

        // then
        assertEquals(2, scanParams.size());
        Assert.assertEquals("l", scanParams.get(0).getFlag());
        Assert.assertEquals("value", scanParams.get(0).getValue());
        Assert.assertEquals("p", scanParams.get(1).getFlag());
        Assert.assertEquals("", scanParams.get(1).getValue());


    }

    @Test
    public void scan_should_return_args_list_with_two_args_having_value_given_input_has_two_flags_with_value() throws LexicalException {
        // given
        Args parser = new Args();
        String paramStr = "-l    value -p flag";

        // when
        List<Arg> scanParams = parser.scan(paramStr);

        // then
        assertEquals(2, scanParams.size());
        Assert.assertEquals("l", scanParams.get(0).getFlag());
        Assert.assertEquals("value", scanParams.get(0).getValue());
        Assert.assertEquals("p", scanParams.get(1).getFlag());
        Assert.assertEquals("flag", scanParams.get(1).getValue());

    }

    @Test
    public void scan_should_return_args_list_with_one_arg_having_value_given_input_has_two_flags_one_with_value_and_have_spaces_between_flags() throws LexicalException {
        // given
        Args parser = new Args();
        String paramStr = "-l value   -p";

        // when
        List<Arg> scanParams = parser.scan(paramStr);

        // then
        assertEquals(2, scanParams.size());
        Assert.assertEquals("l", scanParams.get(0).getFlag());
        Assert.assertEquals("value", scanParams.get(0).getValue());
        Assert.assertEquals("p", scanParams.get(1).getFlag());
        Assert.assertEquals("", scanParams.get(1).getValue());

    }

    @Test
    public void scan_should_return_args_list_with_one_arg_having_value_contain_2nd_flag_given_input_has_two_flags_one_with_value_and_missed_space_between_value_and_next_flag() throws LexicalException {
        // given
        Args parser = new Args();
        String paramStr = "-l value-p";
        expectedException.expect(LexicalException.class);
        expectedException.expectMessage("value should not include -");

        // when
        List<Arg> scanParams = parser.scan(paramStr);
        
    }

    @Test(expected = LexicalException.class)
    public void scan_should_return_lexical_error_given_input_has_one_flag_but_has_one_space_between_dash_and_flag() throws LexicalException {
        // given
        Args parser = new Args();
        String paramStr = "- l";

        // when
        List<Arg> scanParams = parser.scan(paramStr);

    }

    @Test(expected = LexicalException.class)
    public void scan_should_return_lexical_error_given_input_has_two_flag_follow_one_dash() throws LexicalException {
        // given
        Args parser = new Args();
        String paramStr = "-fv";

        // when
        List<Arg> scanParams = parser.scan(paramStr);

    }

    @Test
    public void scan_should_scan_right_given_input_is_3_phase() throws LexicalException {
        // given
        Args parser = new Args();
        String paramStr = "-l value -p flag -d fla";

        // when
        List<Arg> scanParams = parser.scan(paramStr);

        // then
        assertEquals(3, scanParams.size());
        Assert.assertEquals("l", scanParams.get(0).getFlag());
        Assert.assertEquals("value", scanParams.get(0).getValue());
        Assert.assertEquals("p", scanParams.get(1).getFlag());
        Assert.assertEquals("flag", scanParams.get(1).getValue());
        Assert.assertEquals("d", scanParams.get(2).getFlag());
        Assert.assertEquals("fla", scanParams.get(2).getValue());
    }

    @Test(expected = LexicalException.class)
    public void scan_should_not_accept_multiple_values_split_with_space() throws LexicalException {
        // given
        Args parser = new Args();
        String paramStr = "-l value value -p";

        // when
        List<Arg> scanParams = parser.scan(paramStr);
    }

}
