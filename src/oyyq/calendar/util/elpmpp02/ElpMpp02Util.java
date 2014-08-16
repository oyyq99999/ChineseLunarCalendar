package oyyq.calendar.util.elpmpp02;

import static java.lang.Math.PI;
import static oyyq.calendar.util.MathUtil.secondsToRadians;
import static oyyq.calendar.util.elpmpp02.ElpMpp02Constants.*;

public class ElpMpp02Util {

    /**
     * D = W1 - T + 180˚
     * 
     * @param t
     *            儒略世纪数
     * @return D的值(rad)
     */
    private static double getDu(double t) {
        return getW1u(t) - getTu(t) + PI;
    }

    /**
     * F = W1 - W3
     * 
     * @param t
     *            儒略世纪数
     * @return F的值(rad)
     */
    private static double getFu(double t) {
        return getW1u(t) - getW3u(t);
    }

    /**
     * l = W1 - W2
     * 
     * @param t
     *            儒略世纪数
     * @return l的值(rad)
     */
    private static double getL(double t) {
        return getW1u(t) - getW2u(t);
    }

    /**
     * lp = T - Omp
     * 
     * @param t
     *            儒略世纪数
     * @return lp的值(rad)
     */
    private static double getLp(double t) {
        return getTu(t) - getOmegap(t);
    }

    /**
     * Me(rcury) = 252˚15´03.216919˝ + 538101628.68888˝ * t
     * 
     * @param t
     *            儒略世纪数
     * @return Me的值
     */
    private static double getMeu(double t) {
        return secondsToRadians(LAMBDA_ME_0 + LAMBDA_ME_1 * t);
    }

    /**
     * V(enus) = 181˚58´44.758419˝ + 210664136.45777˝ * t
     * 
     * @param t
     *            儒略世纪数
     * @return V的值
     */
    private static double getVu(double t) {
        return secondsToRadians(LAMBDA_V_0 + LAMBDA_V_1 * t);
    }

    /**
     * Ma(rs) = 355˚26´′03.642778˝ + 68905077.65936˝ * t
     * 
     * @param t
     *            儒略世纪数
     * @return Ma的值
     */
    private static double getMau(double t) {
        return secondsToRadians(LAMBDA_MA_0 + LAMBDA_MA_1 * t);
    }

    /**
     * J(upiter) = 34˚21´05.379392˝ + 10925660.57335˝ * t
     * 
     * @param t
     *            儒略世纪数
     * @return J的值
     */
    private static double getJu(double t) {
        return secondsToRadians(LAMBDA_J_0 + LAMBDA_J_1 * t);
    }

    /**
     * S(aturn) = 50˚04´38.902495˝ + 4399609.33632˝ * t
     * 
     * @param t
     *            儒略世纪数
     * @return S的值
     */
    private static double getSu(double t) {
        return secondsToRadians(LAMBDA_S_0 + LAMBDA_S_1 * t);
    }

    /**
     * U(ranus) = 314˚03´04.354234˝ + 1542482.57845˝ * t
     * 
     * @param t
     *            儒略世纪数
     * @return U的值
     */
    private static double getUu(double t) {
        return secondsToRadians(LAMBDA_U_0 + LAMBDA_U_1 * t);
    }

    /**
     * N(eptune) = 304˚20´56.808371˝ + 786547.89700˝ * t
     * 
     * @param t
     *            儒略世纪数
     * @return N的值
     */
    private static double getNu(double t) {
        return secondsToRadians(LAMBDA_N_0 + LAMBDA_N_1 * t);
    }

    /**
     * zeta = W1 + (5029.0966˝ - 0.29965˝) * t
     * 
     * @param t
     *            儒略世纪数
     * @return zeta的值
     */
    private static double getZeta(double t) {
        return secondsToRadians(getW1u(t) + (P - DELTAU_P) * t);
    }

    /**
     * W1 = 218˚18´59.95571˝ + 1732559343.73604˝ * t - 6.8084˝ * t² + 0.006604˝ * t³ - 0.00003169˝ *
     * t⁴
     * 
     * @param t
     *            儒略世纪数
     * @return W1的值(rad)
     */
    private static double getW1u(double t) {
        double t2 = t * t;
        double t3 = t2 * t;
        double t4 = t3 * t;
        return secondsToRadians(W1_0 + W1_1 * t - W1_2 * t2 + W1_3 * t3 - W1_4 * t4);
    }

    /**
     * W2 = 83˚21´11.67475˝ + 14643420.3171˝ * t - 38.2631˝ * t² - 0.045047˝ * t³ + 0.00021301˝ * t⁴
     * 
     * @param t
     *            儒略世纪数
     * @return W2的值(rad)
     */
    private static double getW2u(double t) {
        double t2 = t * t;
        double t3 = t2 * t;
        double t4 = t3 * t;
        return secondsToRadians(W2_0 + W2_1 * t - W2_2 * t2 - W2_3 * t3 + W2_4 * t4);
    }

    /**
     * W3 = 125˚02´40.39816˝ + 6967919.5383˝ * t - 6.3590˝ * t² + 0.007625˝ * t³ + 0.00003586˝ * t⁴
     * 
     * @param t
     *            儒略世纪数
     * @return W3的值(rad)
     */
    private static double getW3u(double t) {
        double t2 = t * t;
        double t3 = t2 * t;
        double t4 = t3 * t;
        return secondsToRadians(W3_0 + W3_1 * t - W3_2 * t2 + W3_3 * t3 + W3_4 * t4);
    }

    /**
     * T = 100˚27´59.13885˝ + 129597742.2930˝ * t - 0.0202˝ * t² + 0.000009˝ * t³ + 0.00000015˝ * t⁴
     * 
     * @param t
     *            儒略世纪数
     * @return T的值(rad)
     */
    private static double getTu(double t) {
        double t2 = t * t;
        double t3 = t2 * t;
        double t4 = t3 * t;
        return secondsToRadians(T_0 + T_1 * t - T_2 * t2 + T_3 * t3 + T_4 * t4);
    }

    /**
     * omegap = 102˚56´14.45766˝ + 1161.24342˝ * t + 0.529265˝ * t² - 0.00011814˝ * t³ +
     * 0.000011379˝ * t⁴
     * 
     * @param t
     *            儒略世纪数
     * @return omegap的值(rad)
     */
    private static double getOmegap(double t) {
        double t2 = t * t;
        double t3 = t2 * t;
        double t4 = t3 * t;
        return secondsToRadians(GOMEGAP_0 + GOMEGAP_1 * t + GOMEGAP_2 * t2 - GOMEGAP_3 * t3 + GOMEGAP_4
                * t4);
    }

}
