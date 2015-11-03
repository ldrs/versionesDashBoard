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

import java.io.IOException;

/**
 * BlancoSqlFormatter: SQL���`�c�[��. SQL�������߂�ꂽ���[���ɏ]�����`���܂��B
 *
 * �t�H�[�}�b�g�����{���邽�߂ɂ́A��͂����SQL��SQL���Ƃ��đÓ��ł��邱�Ƃ��O������ƂȂ�܂��B
 *
 * ���̃N���X����������SQL���`�̃��[���ɂ��ẮA���LURL���Q�Ƃ��������B
 * http://homepage2.nifty.com/igat/igapyon/diary/2005/ig050613.html
 *
 * BlancoSqlFormatterException : SQL���`�c�[���̗�O��\���܂��B
 *
 * @author IGA Tosiki : �V�K�쐬 at 2005.08.03
 */
@SuppressWarnings("serial")
public class BlancoSqlFormatterException extends IOException {

    /**
     * ��O�̃R���X�g���N�^
     */
    public BlancoSqlFormatterException() {
        super();
    }

    /**
     * ��O�̃R���X�g���N�^
     *
     * @param argMessage
     */
    public BlancoSqlFormatterException(final String argMessage) {
        super(argMessage);
    }
}
