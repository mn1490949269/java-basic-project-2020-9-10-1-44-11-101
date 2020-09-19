package com.thoughtworks.args;

import com.thoughtworks.args.exceptions.LexicalException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Args {
    public List<Arg> scan(String paramStr) throws LexicalException {
        //shift commdn f6 -> change the type of variable
        validateValueFormat(paramStr);
        validateFlagFormat(paramStr);

        List<String> params = Arrays.asList(paramStr.split("-"));
        List<Arg> args = params.stream().map(param -> {
            Arg arg = analysisArg(param);
            return arg;
        }).filter(arg -> !arg.getFlag().isEmpty()).collect(Collectors.toList());

        validateFlagLetterLimit(args);

        return args;
    }

    private Arg analysisArg(String param) {
        List<String> argStrs = Arrays.asList(param.split("\\s"))
                .stream()
                .filter(argStr -> !argStr.trim().isEmpty())
                .collect(Collectors.toList());
        return argStrs.size() == 0 ? new Arg("", "")
                : new Arg(argStrs.get(0), argStrs.size() > 1 ? argStrs.get(1) : "");
    }

    private void validateFlagLetterLimit(List<Arg> args) throws LexicalException {
        if (args.stream().filter(arg -> arg.getFlag().length() > 1).findAny().isPresent()) {
            throw new LexicalException("flag can not include 2 latters");
        }
    }

    private void validateFlagFormat(String paramStr) throws LexicalException {
        List<String> params2 = Arrays.asList(paramStr.split("- "));
        if (params2.size() > 1) {
            throw new LexicalException("flag should beside - without whitespace");

        }
    }

    private void validateValueFormat(String paramStr) throws LexicalException {
        paramStr = " " + paramStr;
        List<String> argStrs = Arrays.asList(paramStr.split(" -"));
        if (!argStrs.stream()
                .filter(param1 -> param1.contains("-"))
                .findAny().orElse("").isEmpty()) {
            throw new LexicalException("value should not include -");
        }

        if (argStrs.stream()
                .filter(param -> param.split("\\s+").length > 2)
                .findAny().isPresent()) {
            throw new LexicalException("2 values are not allowed ");
        }
    }
}
