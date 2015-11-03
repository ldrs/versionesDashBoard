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

import java.util.ArrayList;
import java.util.List;

import rd.huma.dashboard.util.sql.format.valueobject.BlancoSqlToken;

/**
 * BlancoSqlFormatter: SQL���`�c�[��. SQL�������߂�ꂽ���[���ɏ]�����`���܂��B
 *
 * �t�H�[�}�b�g�����{���邽�߂ɂ́A��͂����SQL��SQL���Ƃ��đÓ��ł��邱�Ƃ��O������ƂȂ�܂��B
 *
 * ���̃N���X����������SQL���`�̃��[���ɂ��ẮA���LURL���Q�Ƃ��������B
 * http://homepage2.nifty.com/igat/igapyon/diary/2005/ig050613.html
 *
 * ���̃N���X�� SQL������͂��镔���ł��B<br>
 * 2005.08.12 Tosiki Iga: �������̃��[�e�B���e�B���\�b�h�� public static�����܂����B<br>
 * 2005.08.12 Tosiki Iga: 65535(���Ƃ�-1)�̓z���C�g�X�y�[�X�Ƃ��Ĉ����悤�ύX���܂��B
 *
 * @author WATANABE Yoshinori (a-san) : original version at 2005.07.04.
 * @author IGA Tosiki : marge into blanc Framework at 2005.07.04
 */
public class BlancoSqlParser {

    /**
     * ��͑O�̕�����
     */
    private String fBefore;

    /**
     * ��͒��̕����B
     */
    private char fChar;

    /**
     * ��͒��̈ʒu
     */
    private int fPos;

    /**
     * �Q��������Ȃ�L���B
     *
     * �Ȃ��A|| �͕����񌋍��ɂ�����܂��B
     */
    private static final String[] twoCharacterSymbol = { "<>", "<=", ">=", "||" };

    /**
     * �p�[�T�̃C���X�^���X���쐬���܂��B
     */
    public BlancoSqlParser() {
    }

    /**
     * �^����ꂽ�������A�z���C�g�X�y�[�X�������ǂ����𔻒肵�܂��B
     *
     * @param argChar
     * @return
     */
    public static boolean isSpace(final char argChar) {
        // 2005.07.26 Tosiki Iga \r �������͈͂Ɋ܂߂�K�v������܂��B
        // 2005.08.12 Tosiki Iga 65535(���Ƃ�-1)�̓z���C�g�X�y�[�X�Ƃ��Ĉ����悤�ύX���܂��B
        return argChar == ' ' || argChar == '\t' || argChar == '\n'
                || argChar == '\r' || argChar == 65535;
    }

    /**
     * �����Ƃ��ĔF�����đÓ����ǂ����𔻒肵�܂��B
     *
     * �S�p�����Ȃǂ������Ƃ��ĔF�������e������̂Ɣ��f���܂�<br>
     * �����̃��\�b�h��BlancoSqlEditorPlugin����Q�Ƃ���܂��B
     *
     * @param argChar
     * @return
     */
    public static boolean isLetter(final char argChar) {
        // SQL�ɂ����� �A���_�[�X�R�A�͉p���̒��Ԃł�.
        // blanco �ɂ����� # �͉p���̒��Ԃł�.
        // �����ɓ�{����܂߂Ȃ��Ă͂Ȃ�Ȃ��B
        // return ('A' <= c && c <= 'Z') || ('a' <= c && c <= 'z')
        // || (c == '_' || c == '#');
        if (isSpace(argChar)) {
            return false;
        }
        if (isDigit(argChar)) {
            return false;
        }
        if (isSymbol(argChar)) {
            return false;
        }
        return true;
    }

    /**
     * �������ǂ����𔻒肵�܂��B
     *
     * @param argChar
     * @return
     */
    public static boolean isDigit(final char argChar) {
        return '0' <= argChar && argChar <= '9';
    }

    /**
     * �L�����ǂ����𔻒肵�܂��B
     *
     * @param argChar
     * @return
     */
    public static boolean isSymbol(final char argChar) {
        switch (argChar) {
        case '"': // double quote
        case '?': // question mark
        case '%': // percent
        case '&': // ampersand
        case '\'': // quote
        case '(': // left paren
        case ')': // right paren
        case '|': // vertical bar
        case '*': // asterisk
        case '+': // plus sign
        case ',': // comma
        case '-': // minus sign
        case '.': // period
        case '/': // solidus
        case ':': // colon
        case ';': // semicolon
        case '<': // less than operator
        case '=': // equals operator
        case '>': // greater than operator

            // blanco�ł� # �͕�����̈ꕔ�ł� case '#':
            // �A���_�[�X�R�A�͋L���Ƃ͈����܂��� case '_': //underscore
            // ����ȍ~�̕����̈����͕ۗ�
            // case '!':
            // case '$':
            // case '[':
            // case '\\':
            // case ']':
            // case '^':
            // case '{':
            // case '}':
            // case '~':
            return true;
        default:
            return false;
        }
    }

    /**
     * �g�[�N�������ɐi�߂܂��B
     *
     * pos��i�߂�Bs�Ɍ��ʂ�Ԃ��Btype�ɂ��̎�ނ�ݒ肷��B
     *
     * �s����SQL�̏ꍇ�A��O���������܂��B �����ł́A���@�`�F�b�N�͍s���Ă��Ȃ��_�ɒ��ڂ��Ă��������B
     *
     * @return �g�[�N����Ԃ�.
     */
    BlancoSqlToken nextToken() {
        int start_pos = fPos;
        if (fPos >= fBefore.length()) {
            fPos++;
            return new BlancoSqlToken(BlancoSqlTokenConstants.END, "",
                    start_pos);
        }

        fChar = fBefore.charAt(fPos);

        if (isSpace(fChar)) {
            String workString = "";
            for (;;) {
                workString += fChar;
                fChar = fBefore.charAt(fPos);
                if (!isSpace(fChar)) {
                    return new BlancoSqlToken(BlancoSqlTokenConstants.SPACE,
                            workString, start_pos);
                }
                fPos++;
                if (fPos >= fBefore.length()) {
                    return new BlancoSqlToken(BlancoSqlTokenConstants.SPACE,
                            workString, start_pos);
                }
            }
        } else if (fChar == ';') {
            fPos++;
            // 2005.07.26 Tosiki Iga �Z�~�R�����͏I�������ł͂Ȃ��悤�ɂ���B
            return new BlancoSqlToken(BlancoSqlTokenConstants.SYMBOL, ";",
                    start_pos);
        } else if (isDigit(fChar)) {
            String s = "";
            while (isDigit(fChar) || fChar == '.') {
                // if (ch == '.') type = Token.REAL;
                s += fChar;
                fPos++;

                if (fPos >= fBefore.length()) {
                    // �����𒴂��Ă���ꍇ�ɂ͏������f���܂��B
                    break;
                }

                fChar = fBefore.charAt(fPos);
            }
            return new BlancoSqlToken(BlancoSqlTokenConstants.VALUE, s,
                    start_pos);
        } else if (isLetter(fChar)) {
            String s = "";
            // �����񒆂̃h�b�g�ɂ��ẮA������ƈ�̂Ƃ��čl����B
            while (isLetter(fChar) || isDigit(fChar) || fChar == '.') {
                s += fChar;
                fPos++;
                if (fPos >= fBefore.length()) {
                    break;
                }

                fChar = fBefore.charAt(fPos);
            }
            for (int i = 0; i < BlancoSqlConstants.SQL_RESERVED_WORDS.length; i++) {
                if (s
                        .compareToIgnoreCase(BlancoSqlConstants.SQL_RESERVED_WORDS[i]) == 0) {
                    return new BlancoSqlToken(BlancoSqlTokenConstants.KEYWORD,
                            s, start_pos);
                }
            }
            return new BlancoSqlToken(BlancoSqlTokenConstants.NAME, s,
                    start_pos);
        }
        // single line comment
        else if (fChar == '-') {
            fPos++;
            char ch2 = fBefore.charAt(fPos);
            // -- ����Ȃ������Ƃ�
            if (ch2 != '-') {
                return new BlancoSqlToken(BlancoSqlTokenConstants.SYMBOL, "-",
                        start_pos);
            }
            fPos++;
            String s = "--";
            for (;;) {
                fChar = fBefore.charAt(fPos);
                s += fChar;
                fPos++;
                if (fChar == '\n' || fPos >= fBefore.length()) {
                    return new BlancoSqlToken(BlancoSqlTokenConstants.COMMENT,
                            s, start_pos);
                }
            }
        }
        // �}���`���C���R�����g
        else if (fChar == '/') {
            fPos++;
            char ch2 = fBefore.charAt(fPos);
            // /* ����Ȃ������Ƃ�
            if (ch2 != '*') {
                return new BlancoSqlToken(BlancoSqlTokenConstants.SYMBOL, "/",
                        start_pos);
            }

            String s = "/*";
            fPos++;
            int ch0 = -1;
            for (;;) {
                ch0 = fChar;
                fChar = fBefore.charAt(fPos);
                s += fChar;
                fPos++;
                if (ch0 == '*' && fChar == '/') {
                    return new BlancoSqlToken(BlancoSqlTokenConstants.COMMENT,
                            s, start_pos);
                }
            }
        } else if (fChar == '\'') {
            fPos++;
            String s = "'";
            for (;;) {
                fChar = fBefore.charAt(fPos);
                s += fChar;
                fPos++;
                if (fChar == '\'') {
                    return new BlancoSqlToken(BlancoSqlTokenConstants.VALUE, s,
                            start_pos);
                }
            }
        } else if (fChar == '\"') {
            fPos++;
            String s = "\"";
            for (;;) {
                fChar = fBefore.charAt(fPos);
                s += fChar;
                fPos++;
                if (fChar == '\"') {
                    return new BlancoSqlToken(BlancoSqlTokenConstants.NAME, s,
                            start_pos);
                }
            }
        }

        else if (isSymbol(fChar)) {
            // �L��
            String s = "" + fChar;
            fPos++;
            if (fPos >= fBefore.length()) {
                return new BlancoSqlToken(BlancoSqlTokenConstants.SYMBOL, s,
                        start_pos);
            }
            // �Q�����̋L�����ǂ������ׂ�
            char ch2 = fBefore.charAt(fPos);
            for (int i = 0; i < twoCharacterSymbol.length; i++) {
                if (twoCharacterSymbol[i].charAt(0) == fChar
                        && twoCharacterSymbol[i].charAt(1) == ch2) {
                    fPos++;
                    s += ch2;
                    break;
                }
            }
            return new BlancoSqlToken(BlancoSqlTokenConstants.SYMBOL, s,
                    start_pos);
        } else {
            fPos++;
            return new BlancoSqlToken(BlancoSqlTokenConstants.UNKNOWN, ""
                    + fChar, start_pos);
        }
    }

    /**
     * SQL��������g�[�N���̔z��ɕϊ����܂��B
     *
     * @param argSql
     *            �ϊ��O��SQL��
     * @return Token�̔z��
     */
    public List<BlancoSqlToken> parse(final String argSql) {
        fPos = 0;
        fBefore = argSql;

        final List<BlancoSqlToken> list = new ArrayList<BlancoSqlToken>();
        for (;;) {
            final BlancoSqlToken token = nextToken();
            if (token.getType() == BlancoSqlTokenConstants.END) {
                break;
            }

            list.add(token);
        }
        return list;
    }
}
