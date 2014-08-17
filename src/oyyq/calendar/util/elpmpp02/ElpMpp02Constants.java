package oyyq.calendar.util.elpmpp02;

import static oyyq.calendar.util.MathUtil.dmsToRadians;
import static oyyq.calendar.util.MathUtil.secondsToRadians;

class ElpMpp02Constants {

    static final double P                   = 5029.0966;
    static final double DELTAU_P            = -0.29965;

    static final double LAMBDA_ME_0         = dmsToRadians(252, 15, 3.216919);
    static final double LAMBDA_ME_1         = secondsToRadians(538101628.68888);

    static final double LAMBDA_V_0          = dmsToRadians(181, 58, 44.758419);
    static final double LAMBDA_V_1          = secondsToRadians(210664136.45777);

    static final double LAMBDA_MA_0         = dmsToRadians(355, 26, 3.642778);
    static final double LAMBDA_MA_1         = secondsToRadians(68905077.65936);

    static final double LAMBDA_J_0          = dmsToRadians(34, 21, 5.379392);
    static final double LAMBDA_J_1          = secondsToRadians(10925660.57335);

    static final double LAMBDA_S_0          = dmsToRadians(50, 4, 38.902495);
    static final double LAMBDA_S_1          = secondsToRadians(4399609.33632);

    static final double LAMBDA_U_0          = dmsToRadians(314, 3, 4.354234);
    static final double LAMBDA_U_1          = secondsToRadians(1542482.57845);

    static final double LAMBDA_N_0          = dmsToRadians(304, 20, 56.808371);
    static final double LAMBDA_N_1          = secondsToRadians(786547.89700);

    static final double DELTAU_W1_0_DE      = -0.07008;
    static final double DELTAU_W2_0_DE      = +0.20794;
    static final double DELTAU_W3_0_DE      = -0.07215;
    static final double DELTAU_W1_1_DE      = -0.35106;
    static final double DELTAU_NU_DE        = DELTAU_W1_1_DE;
    static final double DELTAU_W2_1_DE      = +0.08017;
    static final double DELTAU_W3_1_DE      = -0.04317;
    static final double DELTAU_W1_2_DE      = -0.03743;
    static final double DELTAU_GGAMMA_DE    = +0.00085;
    static final double DELTAU_E_DE         = -0.00006;
    static final double DELTAU_T_0_DE       = -0.00033;
    static final double DELTAU_T_1_DE       = +0.00732;
    static final double DELTAU_NP_DE        = DELTAU_T_1_DE;
    static final double DELTAU_GOMEGAP_0_DE = -0.00749;
    static final double DELTAU_EP_DE        = +0.00224;

    static final double DELTA_GNU           = 0.55604 + DELTAU_W1_1_DE;
    static final double DELTA_GGAMMA        = -0.08066 + DELTAU_GGAMMA_DE;
    static final double DELTA_E             = 0.01789 + DELTAU_E_DE;
    static final double DELTA_EP            = -0.12879 + DELTAU_EP_DE;
    static final double DELTA_NP            = -0.0642 + DELTAU_NP_DE;

    static final double BP_21               = +0.311079095;
    static final double BP_22               = -0.004482398;
    static final double BP_23               = -0.001102485;
    static final double BP_24               = +0.001056062;
    static final double BP_25               = +0.000050928;

    static final double BP_31               = -0.103837907;
    static final double BP_32               = +0.000668287;
    static final double BP_33               = -0.001298072;
    static final double BP_34               = -0.000178028;
    static final double BP_35               = -0.000037342;

    static final double DELTAU_W1_3         = -0.00018865;
    static final double DELTAU_W1_4         = -0.00001024;
    static final double DELTAU_W2_2         = +0.00470602;
    static final double DELTAU_W2_3         = -0.00025213;
    static final double DELTAU_W3_2         = -0.00261070;
    static final double DELTAU_W3_3         = -0.00010712;

    static final double W1_0                = dmsToRadians(218, 18, 59.95571 + DELTAU_W1_0_DE);
    static final double W1_1                = secondsToRadians(1732559343.73604 + DELTAU_W1_1_DE);
    static final double W1_2                = secondsToRadians(-6.8084 + DELTAU_W1_2_DE);
    static final double W1_3                = secondsToRadians(0.006604 + DELTAU_W1_3);
    static final double W1_4                = secondsToRadians(-0.00003169 + DELTAU_W1_4);

    static final double W2_0                = dmsToRadians(83, 21, 11.67475 + DELTAU_W2_0_DE);
    static double       W2_1                = secondsToRadians(14643420.3171 + DELTAU_W2_1_DE);
    static final double W2_2                = secondsToRadians(-38.2631 + DELTAU_W2_2);
    static final double W2_3                = secondsToRadians(-0.045047 + DELTAU_W2_3);
    static final double W2_4                = secondsToRadians(0.00021301);

    static final double W3_0                = dmsToRadians(125, 2, 40.39816 + DELTAU_W3_0_DE);
    static double       W3_1                = secondsToRadians(-6967919.5383 + DELTAU_W3_1_DE);
    static final double W3_2                = secondsToRadians(6.3590 + DELTAU_W3_2);
    static final double W3_3                = secondsToRadians(0.007625 + DELTAU_W3_3);
    static final double W3_4                = secondsToRadians(-0.00003586);

    static final double T_0                 = dmsToRadians(100, 27, 59.13885 + DELTAU_T_0_DE);
    static final double T_1                 = secondsToRadians(129597742.2930 + DELTAU_T_1_DE);
    static final double T_2                 = secondsToRadians(-0.0202);
    static final double T_3                 = secondsToRadians(0.000009);
    static final double T_4                 = secondsToRadians(0.00000015);

    static final double GOMEGAP_0           = dmsToRadians(102, 56, 14.45766 + DELTAU_GOMEGAP_0_DE);
    static final double GOMEGAP_1           = secondsToRadians(1161.24342);
    static final double GOMEGAP_2           = secondsToRadians(0.529265);
    static final double GOMEGAP_3           = secondsToRadians(-0.00011814);
    static final double GOMEGAP_4           = secondsToRadians(0.000011379);

    static final double GNU                 = W1_1;
    static final double NP                  = T_1;

    static final double M                   = NP / GNU;
    static final double GALPHA              = 0.002571881;

    static {
        W2_1 += secondsToRadians(W2_1 / GNU - M * (BP_21 + 2 / 3.0 * GALPHA / M * BP_25))
                * DELTAU_W1_1_DE + (BP_21 + 2 / 3.0 * GALPHA / M * BP_25) * DELTAU_T_1_DE + GNU
                * (BP_22 * DELTAU_GGAMMA_DE + BP_23 * DELTAU_E_DE + BP_24 * DELTAU_EP_DE);
        W3_1 += secondsToRadians(W2_1 / GNU - M * (BP_21 + 2 / 3.0 * GALPHA / M * BP_25))
                * DELTAU_W1_1_DE + (BP_21 + 2 / 3.0 * GALPHA / M * BP_25) * DELTAU_T_1_DE + GNU
                * (BP_22 * DELTAU_GGAMMA_DE + BP_23 * DELTAU_E_DE + BP_24 * DELTAU_EP_DE);
    }
}
