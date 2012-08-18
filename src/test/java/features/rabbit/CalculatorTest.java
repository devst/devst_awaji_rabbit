package features.rabbit;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Before;
import org.junit.Test;

import features.rabbit.Calculator;

public class CalculatorTest {

    Calculator sut;

    @Before
    public void setUp() {
        sut = new Calculator();
    }

    @Test
    public void plusと2つの10進数値をexecuteCalculateに渡すと2つの数値の加算結果の文字列が返る() {

        assertThat(sut.executeCalc("+", "5", "3"),is("7"));
    }

    @Test
    public void minusと2つの10進数値をexecuteCalculateに渡すと2つの数値の減算結果の文字列が返る() {

        assertThat(sut.executeCalc("-", "5", "3"),is("2"));
    }

    @Test
    public void multiplyと2つの10進数値をexecuteCalculateに渡すと2つの数値の乗算結果の文字列が返る() {

        assertThat(sut.executeCalc("*", "5", "3"),is("15"));
    }

    @Test
    public void divideと2つの10進数値をexecuteCalculateに渡すと2つの数値の除算結果の文字列が返る() {

        assertThat(sut.executeCalc("/", "5", "3"),is("1.667"));
    }

    @Test(expected=RuntimeException.class)
    public void 無効な演算子の空文字列の場合はIllegalArgumentException() {

        sut.executeCalc("?", "5", "3");
    }

    @Test
    public void 四則演算式の文字列をexecuteに渡すと演算結果が返る() {

        assertThat(sut.execute("5+3"),is("8"));
        assertThat(sut.execute("5-3"),is("2"));
        assertThat(sut.execute("5*3"),is("15"));
        assertThat(sut.execute("5/3"),is("1.667"));
    }

    @Test(expected=RuntimeException.class)
    public void 数値の間に複数個演算子のある式がexecuteに渡されるとRuntimeException() {

        sut.execute("5++3");
    }

    @Test(expected=RuntimeException.class)
    public void 演算子の右辺にしか数値のない式がexecuteに渡されるとRuntimeException() {

        sut.execute("-3");
    }

    @Test(expected=RuntimeException.class)
    public void 演算子の左辺にしか数値のない式がexecuteに渡されるとRuntimeException() {

        sut.execute("3+");
    }

    @Test(expected=RuntimeException.class)
    public void 無効な演算子を含む式がexecuteに渡されるとRuntimeException() {

        sut.execute("3+");
    }

    @Test(expected=RuntimeException.class)
    public void 右辺の数値が0を四則演算式がexecuteに渡されるとRuntimeException() {

        sut.execute("1+0");
    }

    @Test(expected=RuntimeException.class)
    public void 左辺の数値が0を四則演算式がexecuteに渡されるとRuntimeException() {

        sut.execute("0+1");
    }
}
