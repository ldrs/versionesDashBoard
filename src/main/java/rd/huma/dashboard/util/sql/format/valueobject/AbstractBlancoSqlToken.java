/*
 * ���̃\�[�X�R�[�h�� blanco Framework�ɂ�莩����������܂����B
 */
package rd.huma.dashboard.util.sql.format.valueobject;

/**
 * blancoSqlFormatter�ŗ��p�����g�[�N��������킷�o�����[�I�u�W�F�N�g�B
 */
public class AbstractBlancoSqlToken {
    /**
     * �t�B�[���h [type]
     *
     * ���ڂ̌^ [int]<br>
     * �g�[�N����ʂ�����킵�܂��B
     */
    private int fType;

    /**
     * �t�B�[���h [string]
     *
     * ���ڂ̌^ [java.lang.String]<br>
     * �g�[�N���̎��ۂ̕����������킵�܂��B
     */
    private String fString;

    /**
     * �t�B�[���h [pos]
     *
     * ���ڂ̌^ [int]<br>
     * �K��l   [-1]<br>
     * �\���A�R�����g�A�l�Ȃǂ̃g�[�N���̈ʒu������킷�t�B�[���h�B�\�[�X������̐擪����̈ʒu������킵�܂��B�l�� �[��(ZERO)�I���W���ł��B�f�t�H���g�l�� (-1)�ŁA(-1) �̏ꍇ�ɂ́u�ʒu���ɈӖ����Ȃ��v���Ƃ�����킵�܂��B
     */
    private int fPos = -1;

    /**
     * �t�B�[���h [type]�̃Z�b�^�[���\�b�h
     *
     * ���ڂ̌^ [int]<br>
     * �g�[�N����ʂ�����킵�܂��B
     *
     * @param argType �t�B�[���h[type]�Ɋi�[�������l
     */
    public void setType(final int argType) {
        fType = argType;
    }

    /**
     * �t�B�[���h[type]�̃Q�b�^�[���\�b�h
     *
     * ���ڂ̌^ [int]<br>
     * �g�[�N����ʂ�����킵�܂��B
     *
     * @return �t�B�[���h[type]�Ɋi�[����Ă���l
     */
    public int getType() {
        return fType;
    }

    /**
     * �t�B�[���h [string]�̃Z�b�^�[���\�b�h
     *
     * ���ڂ̌^ [java.lang.String]<br>
     * �g�[�N���̎��ۂ̕����������킵�܂��B
     *
     * @param argString �t�B�[���h[string]�Ɋi�[�������l
     */
    public void setString(final String argString) {
        fString = argString;
    }

    /**
     * �t�B�[���h[string]�̃Q�b�^�[���\�b�h
     *
     * ���ڂ̌^ [java.lang.String]<br>
     * �g�[�N���̎��ۂ̕����������킵�܂��B
     *
     * @return �t�B�[���h[string]�Ɋi�[����Ă���l
     */
    public String getString() {
        return fString;
    }

    /**
     * �t�B�[���h [pos]�̃Z�b�^�[���\�b�h
     *
     * ���ڂ̌^ [int]<br>
     * �\���A�R�����g�A�l�Ȃǂ̃g�[�N���̈ʒu������킷�t�B�[���h�B�\�[�X������̐擪����̈ʒu������킵�܂��B�l�� �[��(ZERO)�I���W���ł��B�f�t�H���g�l�� (-1)�ŁA(-1) �̏ꍇ�ɂ́u�ʒu���ɈӖ����Ȃ��v���Ƃ�����킵�܂��B
     *
     * @param argPos �t�B�[���h[pos]�Ɋi�[�������l
     */
    public void setPos(final int argPos) {
        fPos = argPos;
    }

    /**
     * �t�B�[���h[pos]�̃Q�b�^�[���\�b�h
     *
     * ���ڂ̌^ [int]<br>
     * �K��l   [-1]<br>
     * �\���A�R�����g�A�l�Ȃǂ̃g�[�N���̈ʒu������킷�t�B�[���h�B�\�[�X������̐擪����̈ʒu������킵�܂��B�l�� �[��(ZERO)�I���W���ł��B�f�t�H���g�l�� (-1)�ŁA(-1) �̏ꍇ�ɂ́u�ʒu���ɈӖ����Ȃ��v���Ƃ�����킵�܂��B
     *
     * @return �t�B�[���h[pos]�Ɋi�[����Ă���l
     */
    public int getPos() {
        return fPos;
    }

    /**
     * ���̃o�����[�I�u�W�F�N�g�̕�����\�����擾���܂��B
     *
     * �I�u�W�F�N�g�̃V�����[�͈͂ł���toString����Ȃ��_�ɒ��ӂ��ė��p���Ă��������B
     *
     * @return �o�����[�I�u�W�F�N�g�̕�����\���B
     */
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("blanco.commons.sql.format.valueobject.AbstractBlancoSqlToken[");
        buf.append("type=" + fType);
        buf.append(",string=" + fString);
        buf.append(",pos=" + fPos);
        buf.append("]");
        return buf.toString();
    }
}
