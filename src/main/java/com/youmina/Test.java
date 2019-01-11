package com.youmina;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

/**
 * @author zhangxinhe
 * @date 2018/12/3 11:58
 */
public class Test {

    public static void main(String[] args) {
        ActiveXComponent sap = new ActiveXComponent("Sapi.SpVoice");
        Dispatch sapo = sap.getObject();
        try {
            // 音量 0-100
            sap.setProperty("Volume", new Variant(100));
            // 语音朗读速度 -10 到 +10
            sap.setProperty("Rate", new Variant(0));
            // 执行朗读
            String word ="\n" +
                    "• 参与者资格：所有有米云注册用户均可参与。活动期间每位用户分享邀请码均可获得总值120元的优惠券(有效期7天)，每日分享次数不设上限。\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "• 新用户领取优惠券并完成注册后，视作邀请新用户成功，新用户会获得总值为180元的优惠券，有效期为系统显示为准，同时邀请人获得60元优惠券；邀请的新用户完成首单后，邀请人可再获得60元优惠券(有效期7天)。\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "• 每成功邀请一位好友还可获得10成长值和5优币的奖励，如果好友首次下单并完成付款还可获得奖励20成长值和10优币的奖励。\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "• 为了杜绝刷单等妨碍正常用户的恶意行为，系统会在下单后进行核查，同一手机号、支付宝、有米云帐号、设备号、订单收件人均视为同一用户。对于恶意刷单的用户，取消购买资格。\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "• 有任何疑问请在app内，咨询有米云在线客服。\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "• 有米云保留法律范围内的最终解释权，如有其它疑问请咨询";
            Dispatch.call(sapo, "Speak", new Variant(word));
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            sapo.safeRelease();
            sap.safeRelease();
        }
    }
}
