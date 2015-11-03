/*
 * blanco Framework
 * Copyright (C) 2004-2006 WATANABE Yoshinori
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 */
package rd.huma.dashboard.util.sql.format;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Stack;

import rd.huma.dashboard.util.sql.format.valueobject.BlancoSqlToken;

/**
 * BlancoSqlFormatter: SQL���`�c�[��. SQL�������߂�ꂽ���[���ɏ]�����`���܂��B
 *
 * �t�H�[�}�b�g�����{���邽�߂ɂ́A��͂����SQL��SQL���Ƃ��đÓ��ł��邱�Ƃ��O������ƂȂ�܂��B
 *
 * ���̃N���X����������SQL���`�̃��[���ɂ��ẮA���LURL���Q�Ƃ��������B
 * http://homepage2.nifty.com/igat/igapyon/diary/2005/ig050613.html
 *
 * 2005.08.08 Tosiki Iga: ( ) �ɂ��Ă� (*) �̂悤�ɂЂ�����悤�ɕύX�B <br>
 * 2005.08.03 Tosiki Iga: ���߂�ꂽ��O���X���[����悤�ɕύX���܂����B
 *
 * @author Yoshinori WATANABE (a-san) : original version at 2005.07.04.
 * @author Tosiki Iga : marge into blanc Framework at 2005.07.04
 */
public class BlancoSqlFormatter {
    private final BlancoSqlParser fParser = new BlancoSqlParser();

    private BlancoSqlRule fRule = null;

    /**
     * �ۃJ�b�R���֐��̂��̂��ǂ������o����B
     */
    private Stack<Boolean> functionBracket = new Stack<Boolean>();

    /**
     * SQL���`�c�[���̃C���X�^���X���쐬���܂��B
     *
     * @param argRule
     *            SQL�ϊ����[���B
     */
    public BlancoSqlFormatter(final BlancoSqlRule argRule) {
        fRule = argRule;
    }

    /**
     * �^����ꂽSQL�𐮌`���܂��B
     *
     * 1.��s�ŏI������SQL���́A���`�����s�t���ł���悤�ɂ��܂��B
     *
     * @param argSql
     *            ���`�O��SQL��
     * @return ���`���SQL��
     * @throws BlancoSqlFormatterException
     *             �����I�ɔ���������O�͑S�Ă��̃N���X�ɂȂ�܂��B
     */
    public String format(final String argSql)
            throws BlancoSqlFormatterException {
        functionBracket.clear();
        try {
            boolean isSqlEndsWithNewLine = false;
            if (argSql.endsWith("\n")) {
                isSqlEndsWithNewLine = true;
            }

            List<BlancoSqlToken> list = fParser.parse(argSql);

            list = format(list);

            // �ϊ����ʂ𕶎���ɖ߂��B
            String after = "";
            for (int index = 0; index < list.size(); index++) {
                BlancoSqlToken token = list.get(index);
                after += token.getString();
            }

            if (isSqlEndsWithNewLine) {
                after += "\n";
            }

            return after;
        } catch (Exception ex) {
            final BlancoSqlFormatterException sqlException = new BlancoSqlFormatterException(
                    ex.toString());
            sqlException.initCause(ex);
            throw sqlException;
        }
    }

    /**
     * ����̔z����A�w�肳�ꂽSQL�����K���ɏ]���ĕϊ����܂��B
     *
     * @param argList
     *            �ϊ��O�̎���̔z��BArrayList <Token>
     * @return �ϊ���̎���̔z��BArrayList <Token>
     */
    private List<BlancoSqlToken> format(final List<BlancoSqlToken> argList) {

        // TODO:SQL���`�̃J�X�^�}�C�Y���K�v�ȕ�́A��������A���Ȃ��̍s�����������ɕϊ����ĉ������B
        // �Ȃ�ׂ��V���v���Ŗ��m�Ȑ��`���[���ɂ��Ă��������B
        // �܂��A�N���g��Ȃ��悤�ȕs�K�v�ȑI�����͂�߂܂��傤�B

        // SQL�̑O��ɋ󔒂�����ƍ폜����B
        BlancoSqlToken token = argList.get(0);
        if (token.getType() == BlancoSqlTokenConstants.SPACE) {
            argList.remove(0);
        }

        token = argList.get(argList.size() - 1);
        if (token.getType() == BlancoSqlTokenConstants.SPACE) {
            argList.remove(argList.size() - 1);
        }

        // SQL�L�[���[�h�͑啶���Ƃ���Bor ...
        for (int index = 0; index < argList.size(); index++) {
            token = argList.get(index);
            if (token.getType() == BlancoSqlTokenConstants.KEYWORD) {
                switch (fRule.keyword) {
                case BlancoSqlRule.KEYWORD_NONE:
                    break;
                case BlancoSqlRule.KEYWORD_UPPER_CASE:
                    token.setString(token.getString().toUpperCase());
                    break;
                case BlancoSqlRule.KEYWORD_LOWER_CASE:
                    token.setString(token.getString().toLowerCase());
                    break;
                }
            }
        }

        // ��������A�L���̑O��̋󔒂���������
        for (int index = argList.size() - 1; index >= 1; index--) {
            token = argList.get(index);
            BlancoSqlToken prevToken = argList.get(index - 1);
            if (token.getType() == BlancoSqlTokenConstants.SPACE
                    && (prevToken.getType() == BlancoSqlTokenConstants.SYMBOL || prevToken
                            .getType() == BlancoSqlTokenConstants.COMMENT)) {
                argList.remove(index);
            } else if ((token.getType() == BlancoSqlTokenConstants.SYMBOL || token
                    .getType() == BlancoSqlTokenConstants.COMMENT)
                    && prevToken.getType() == BlancoSqlTokenConstants.SPACE) {
                argList.remove(index - 1);
            } else if (token.getType() == BlancoSqlTokenConstants.SPACE) {
                token.setString(" ");
            }
        }

        // �Q���񂾃L�[���[�h�͂P�̃L�[���[�h�Ƃ݂Ȃ��B(ex."INSERT INTO", "ORDER BY")
        // �ߑ�̌���̓L�[���[�h���Q���Ԃ��Ƃ͂Ȃ��B�Â�����ł́A���R����i�܂�l�Ԃ̌���j��
        // �߂Â��邽�߁A�L�[���[�h���Q���Ԃ��Ƃ��������B�������A�ߑ�ł�"ORDER_BY"�A���邢��"OrderBy"
        // �̂悤�ɁA�ǐ��𑹂Ȃ����ƂȂ��A��͂��₷�����@���̗p���Ă���B
        for (int index = 0; index < argList.size() - 2; index++) {
            BlancoSqlToken t0 = argList.get(index);
            BlancoSqlToken t1 = argList.get(index + 1);
            BlancoSqlToken t2 = argList.get(index + 2);

            if (t0.getType() == BlancoSqlTokenConstants.KEYWORD
                    && t1.getType() == BlancoSqlTokenConstants.SPACE
                    && t2.getType() == BlancoSqlTokenConstants.KEYWORD) {
                if (((t0.getString().equalsIgnoreCase("ORDER") || t0
                        .getString().equalsIgnoreCase("GROUP")) && t2
                        .getString().equalsIgnoreCase("BY"))) {
                    t0.setString(t0.getString() + " " + t2.getString());
                    argList.remove(index + 1);
                    argList.remove(index + 1);
                }
            }

            // Oracle�Ή� begin 2007/10/24 A.Watanabe
            // Oracle�̊O���������Z�q"(+)"���P�̉��Z�q�Ƃ���B
            if (t0.getString().equals("(") && t1.getString().equals("+")
                    && t2.getString().equals(")")) {
                t0.setString("(+)");
                argList.remove(index + 1);
                argList.remove(index + 1);
            }
            // Oracle�Ή� end
        }

        // �C���f���g�𐮂���B
        int indent = 0;
        // �ۃJ�b�R�̃C���f���g�ʒu���o����B
        final Stack<Integer> bracketIndent = new Stack<Integer>();
        BlancoSqlToken prev = new BlancoSqlToken(BlancoSqlTokenConstants.SPACE,
                " ");
        boolean encounterBetween = false;
        for (int index = 0; index < argList.size(); index++) {
            token = argList.get(index);
            if (token.getType() == BlancoSqlTokenConstants.SYMBOL) {
                // indent���P���₵�A'('�̂��Ƃŉ�s�B
                if (token.getString().equals("(")) {
                    functionBracket
                            .push(fRule.isFunction(prev.getString()) ? Boolean.TRUE
                                    : Boolean.FALSE);
                    bracketIndent.push(new Integer(indent));
                    indent++;
                    index += insertReturnAndIndent(argList, index + 1, indent);
                }
                // indent���P���₵�A')'�̑O�ƌ��ŉ�s�B
                else if (token.getString().equals(")")) {
                    indent = bracketIndent.pop().intValue();
                    index += insertReturnAndIndent(argList, index, indent);
                    functionBracket.pop();
                }
                // ','�̑O�ŉ�s
                else if (token.getString().equals(",")) {
                    index += insertReturnAndIndent(argList, index, indent);
                } else if (token.getString().equals(";")) {
                    // 2005.07.26 Tosiki Iga �Ƃ肠�����Z�~�R������SQL�����Ԃ�Ȃ��悤�ɉ��
                    indent = 0;
                    index += insertReturnAndIndent(argList, index, indent);
                }
            } else if (token.getType() == BlancoSqlTokenConstants.KEYWORD) {
                // indent���Q���₵�A�L�[���[�h�̌��ŉ�s
                if (token.getString().equalsIgnoreCase("DELETE")
                        || token.getString().equalsIgnoreCase("SELECT")
                        || token.getString().equalsIgnoreCase("UPDATE")) {
                    indent += 2;
                    index += insertReturnAndIndent(argList, index + 1, indent);
                }
                // indent���P���₵�A�L�[���[�h�̌��ŉ�s
                if (token.getString().equalsIgnoreCase("INSERT")
                        || token.getString().equalsIgnoreCase("INTO")
                        || token.getString().equalsIgnoreCase("CREATE")
                        || token.getString().equalsIgnoreCase("DROP")
                        || token.getString().equalsIgnoreCase("TRUNCATE")
                        || token.getString().equalsIgnoreCase("TABLE")
                        || token.getString().equalsIgnoreCase("CASE")) {
                    indent++;
                    index += insertReturnAndIndent(argList, index + 1, indent);
                }
                // �L�[���[�h�̑O��indent���P���炵�ĉ�s�A�L�[���[�h�̌���indent��߂��ĉ�s�B
                if (token.getString().equalsIgnoreCase("FROM")
                        || token.getString().equalsIgnoreCase("WHERE")
                        || token.getString().equalsIgnoreCase("SET")
                        || token.getString().equalsIgnoreCase("ORDER BY")
                        || token.getString().equalsIgnoreCase("GROUP BY")
                        || token.getString().equalsIgnoreCase("HAVING")) {
                    index += insertReturnAndIndent(argList, index, indent - 1);
                    index += insertReturnAndIndent(argList, index + 1, indent);
                }
                // �L�[���[�h�̑O��indent���P���炵�ĉ�s�A�L�[���[�h�̌���indent��߂��ĉ�s�B
                if (token.getString().equalsIgnoreCase("VALUES")) {
                    indent--;
                    index += insertReturnAndIndent(argList, index, indent);
                }
                // �L�[���[�h�̑O��indent���P���炵�ĉ�s
                if (token.getString().equalsIgnoreCase("END")) {
                    indent--;
                    index += insertReturnAndIndent(argList, index, indent);
                }
                // �L�[���[�h�̑O�ŉ�s
                if (token.getString().equalsIgnoreCase("OR")
                        || token.getString().equalsIgnoreCase("THEN")
                        || token.getString().equalsIgnoreCase("ELSE")) {
                    index += insertReturnAndIndent(argList, index, indent);
                }
                // �L�[���[�h�̑O�ŉ�s
                if (token.getString().equalsIgnoreCase("ON")
                        || token.getString().equalsIgnoreCase("USING")) {
                    index += insertReturnAndIndent(argList, index, indent + 1);
                }
                // �L�[���[�h�̑O�ŉ�s�Bindent�������I�ɂO�ɂ���B
                if (token.getString().equalsIgnoreCase("UNION")
                        || token.getString().equalsIgnoreCase("INTERSECT")
                        || token.getString().equalsIgnoreCase("EXCEPT")) {
                    indent -= 2;
                    index += insertReturnAndIndent(argList, index, indent);
                    index += insertReturnAndIndent(argList, index + 1, indent);
                }
                if (token.getString().equalsIgnoreCase("BETWEEN")) {
                    encounterBetween = true;
                }
                if (token.getString().equalsIgnoreCase("AND")) {
                    // BETWEEN �̂��Ƃ�AND�͉�s���Ȃ��B
                    if (!encounterBetween) {
                        index += insertReturnAndIndent(argList, index, indent);
                    }
                    encounterBetween = false;
                }
            } else if (token.getType() == BlancoSqlTokenConstants.COMMENT) {
                if (token.getString().startsWith("/*")) {
                    // �}���`���C���R�����g�̌�ɉ�s�����B
                    index += insertReturnAndIndent(argList, index + 1, indent);
                }
            }
            prev = token;
        }

        // �ۃJ�b�R�ň͂܂ꂽ (�ЂƂ̍���)�ɂ��Ă͓��ʈ������s���B @author tosiki iga
        for (int index = argList.size() - 1; index >= 4; index--) {
            if (index >= argList.size()) {
                continue;
            }

            BlancoSqlToken t0 = argList.get(index);
            BlancoSqlToken t1 = argList.get(index - 1);
            BlancoSqlToken t2 = argList.get(index - 2);
            BlancoSqlToken t3 = argList.get(index - 3);
            BlancoSqlToken t4 = argList.get(index - 4);

            if (t4.getString().equalsIgnoreCase("(")
                    && t3.getString().trim().equalsIgnoreCase("")
                    && t1.getString().trim().equalsIgnoreCase("")
                    && t0.getString().equalsIgnoreCase(")")) {
                t4.setString(t4.getString() + t2.getString() + t0.getString());
                argList.remove(index);
                argList.remove(index - 1);
                argList.remove(index - 2);
                argList.remove(index - 3);
            }
        }

        // �O��ɃX�y�[�X�����
        for (int index = 1; index < argList.size(); index++) {
            prev = argList.get(index - 1);
            token = argList.get(index);

            if (prev.getType() != BlancoSqlTokenConstants.SPACE
                    && token.getType() != BlancoSqlTokenConstants.SPACE) {
                // �J���}�̌�ɂ̓X�y�[�X���Ȃ�
                if (prev.getString().equals(",")) {
                    continue;
                }
                // �֐����̌��ɂ̓X�y�[�X�͓��Ȃ�
                if (fRule.isFunction(prev.getString())
                        && token.getString().equals("(")) {
                    continue;
                }
                argList.add(index, new BlancoSqlToken(
                        BlancoSqlTokenConstants.SPACE, " "));
            }
        }

        return argList;
    }

    /**
     * ��s�ƃC���f���g��}���.
     *
     * @param argList
     * @param argIndex
     * @param argIndent
     * @return �󔒂�}��ꍇ�͂P���A�󔒂�u���������ꍇ�͂O��Ԃ��B
     */
    private int insertReturnAndIndent(final List<BlancoSqlToken> argList,
            final int argIndex, final int argIndent) {
        // �֐����ł͉�s�͑}��Ȃ�
        if (functionBracket.contains(Boolean.TRUE))
            return 0;
        try {
            // �}��镶������쐬����B
            String s = "\n";
            // �����P�O�ɃV���O�����C���R�����g������Ȃ�A��s�͕s�v�B
            final BlancoSqlToken prevToken = argList.get(argIndex - 1);
            if (prevToken.getType() == BlancoSqlTokenConstants.COMMENT
                    && prevToken.getString().startsWith("--")) {
                s = "";
            }
            // �C���f���g������B
            for (int index = 0; index < argIndent; index++) {
                s += fRule.indentString;
            }

            // �O��ɂ��łɃX�y�[�X������΁A�����u��������B
            BlancoSqlToken token = argList.get(argIndex);
            if (token.getType() == BlancoSqlTokenConstants.SPACE) {
                token.setString(s);
                return 0;
            }

            token = argList.get(argIndex - 1);
            if (token.getType() == BlancoSqlTokenConstants.SPACE) {
                token.setString(s);
                return 0;
            }
            // �O��ɂȂ���΁A�V���ɃX�y�[�X��ǉ�����B
            argList.add(argIndex, new BlancoSqlToken(
                    BlancoSqlTokenConstants.SPACE, s));
            return 1;
        } catch (IndexOutOfBoundsException e) {
            // e.printStackTrace();
            return 0;
        }
    }

    public static void main(final String[] args) throws Exception {
        // ���[����ݒ肷��
        final BlancoSqlRule rule = new BlancoSqlRule();
        rule.keyword = BlancoSqlRule.KEYWORD_UPPER_CASE;
        rule.indentString = "    ";
        final String[] mySqlFuncs = {
                // getNumericFunctions
                "ABS", "ACOS", "ASIN", "ATAN", "ATAN2", "BIT_COUNT", "CEILING",
                "COS", "COT", "DEGREES", "EXP",
                "FLOOR",
                "LOG",
                "LOG10",
                "MAX",
                "MIN",
                "MOD",
                "PI",
                "POW",
                "POWER",
                "RADIANS",
                "RAND",
                "ROUND",
                "SIN",
                "SQRT",
                "TAN",
                "TRUNCATE",
                // getStringFunctions
                "ASCII", "BIN", "BIT_LENGTH", "CHAR", "CHARACTER_LENGTH",
                "CHAR_LENGTH", "CONCAT", "CONCAT_WS", "CONV", "ELT",
                "EXPORT_SET", "FIELD", "FIND_IN_SET", "HEX,INSERT", "INSTR",
                "LCASE", "LEFT", "LENGTH", "LOAD_FILE", "LOCATE", "LOCATE",
                "LOWER", "LPAD", "LTRIM", "MAKE_SET", "MATCH", "MID", "OCT",
                "OCTET_LENGTH", "ORD", "POSITION", "QUOTE", "REPEAT",
                "REPLACE", "REVERSE", "RIGHT", "RPAD", "RTRIM", "SOUNDEX",
                "SPACE", "STRCMP", "SUBSTRING",
                "SUBSTRING",
                "SUBSTRING",
                "SUBSTRING",
                "SUBSTRING_INDEX",
                "TRIM",
                "UCASE",
                "UPPER",
                // getSystemFunctions
                "DATABASE", "USER",
                "SYSTEM_USER",
                "SESSION_USER",
                "PASSWORD",
                "ENCRYPT",
                "LAST_INSERT_ID",
                "VERSION",
                // getTimeDateFunctions
                "DAYOFWEEK", "WEEKDAY", "DAYOFMONTH", "DAYOFYEAR", "MONTH",
                "DAYNAME", "MONTHNAME", "QUARTER", "WEEK", "YEAR", "HOUR",
                "MINUTE", "SECOND", "PERIOD_ADD", "PERIOD_DIFF", "TO_DAYS",
                "FROM_DAYS", "DATE_FORMAT", "TIME_FORMAT", "CURDATE",
                "CURRENT_DATE", "CURTIME", "CURRENT_TIME", "NOW", "SYSDATE",
                "CURRENT_TIMESTAMP", "UNIX_TIMESTAMP", "FROM_UNIXTIME",
                "SEC_TO_TIME", "TIME_TO_SEC" };
        rule.setFunctionNames(mySqlFuncs);
        final BlancoSqlFormatter formatter = new BlancoSqlFormatter(rule);

        // �e�X�g�f�B���N�g�����̃t�@�C�����ꊇ�ŕϊ�����B
        final File[] files = new File("Test").listFiles();
        for (int i = 0; i < files.length; i++) {
            System.out.println("-- " + files[i]);
            // �t�@�C����SQL��ǂݍ���.
            final BufferedReader reader = new BufferedReader(new FileReader(
                    files[i]));
            String before = "";
            while (reader.ready()) {
                String line = reader.readLine();
                if (line == null)
                    break;
                before += line + "\n";
            }
            reader.close();

            // ���`
            System.out.println("[before]\n" + before);
            String after = formatter.format(before);
            System.out.println("[after]\n" + after);
        }
    }
}
