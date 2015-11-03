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

/**
 * BlancoSqlFormatter: SQL���`�c�[��. SQL�������߂�ꂽ���[���ɏ]�����`���܂��B
 *
 * �t�H�[�}�b�g�����{���邽�߂ɂ́A��͂����SQL��SQL���Ƃ��đÓ��ł��邱�Ƃ��O������ƂȂ�܂��B
 *
 * ���̃N���X����������SQL���`�̃��[���ɂ��ẮA���LURL���Q�Ƃ��������B
 * http://homepage2.nifty.com/igat/igapyon/diary/2005/ig050613.html
 *
 * ���̃N���X�� SQL�̕ϊ��K����\���܂��B
 *
 * @author WATANABE Yoshinori (a-san) : original version at 2005.07.04.
 * @author IGA Tosiki : marge into blanc Framework at 2005.07.04
 */
public class BlancoSqlRule {
    /** �L�[���[�h�̕ϊ��K��. */
    int keyword = KEYWORD_UPPER_CASE;

    /** �L�[���[�h�̕ϊ��K��:�������Ȃ� */
    public static final int KEYWORD_NONE = 0;

    /** �L�[���[�h�̕ϊ��K��:�啶���ɂ��� */
    public static final int KEYWORD_UPPER_CASE = 1;

    /** �L�[���[�h�̕ϊ��K��:�������ɂ��� */
    public static final int KEYWORD_LOWER_CASE = 2;

    /**
     * �C���f���g�̕�����. �ݒ�͎��R��͂Ƃ���B�ʏ�� " ", " ", "\t" �̂����ꂩ�B
     */
    String indentString = "    ";

    /**
     * �֐��̖��O�B
     */
    private String[] fFunctionNames = null;

    public void setKeywordCase(int keyword) {
        this.keyword = keyword;
    }

    /**
     * �֐��̖��O���H
     *
     * @param name
     *            ���ׂ閼�O
     * @return �֐��̖��O�̂Ƃ��A<code>true</code> ��Ԃ��B
     */
    boolean isFunction(String name) {
        if (fFunctionNames == null)
            return false;
        for (int i = 0; i < fFunctionNames.length; i++) {
            if (fFunctionNames[i].equalsIgnoreCase(name))
                return true;
        }
        return false;
    }

    /**
     * �֐��̖��O�̔z���o�^���܂��B
     *
     * @param names
     *            �֐����̔z��Bnull�B
     */
    public void setFunctionNames(String[] names) {
        fFunctionNames = names;
    }

    // TODO �J�X�^�}�C�Y�̈�B�J�X�^�}�C�Y���K�v�ȏꍇ�ɂ́A�ȉ��ɋK����ǉ����Ă��������B
}
