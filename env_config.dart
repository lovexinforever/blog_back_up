/*
 * @Descripttion: 
 * @Author: yang.ding
 * @version: 
 * @Date: 2021-06-22 14:58:57
 * @LastEditors: yang.ding
 * @LastEditTime: 2021-06-22 15:05:44
 */
import 'package:flutter_app_2_0/utils/utils.dart';

///环境配置
class EnvConfig {
  ///服务器url
  final String? host;

  final String? rongYunKey;

  /// 分享帖子的链接
  final String? sharePostUrl;

  ///分享话题的链接
  final String? shareTopicUrl;

  ///分销商 url
  final String? shareDistributionUrl;

  /// 邀请有礼url
  final String? shareInviteUrl;

  /// 活动分享url
  final String? shareActivityUrl;

  /// 分享书籍章节url
  final String? shareBookChapterUrl;

  /// 分享书籍详情的url
  final String? shareBookDetailUrl;

  /// 用户隐私协议
  final String? userPrivacy;

  /// 用户协议
  final String? userAgreement;

  /// 分享圈子
  final String? shareGroupUrl;

  ///合伙人分布
  final String? partnerSpreadUrl;

  EnvConfig(
      {this.host,
      this.sharePostUrl,
      this.shareTopicUrl,
      this.shareDistributionUrl,
      this.shareBookChapterUrl,
      this.shareActivityUrl,
      this.userAgreement,
      this.userPrivacy,
      this.shareGroupUrl,
      this.rongYunKey,
      this.shareBookDetailUrl,
      this.shareInviteUrl,
      this.partnerSpreadUrl
      });
}

// 获取的配置信息
class Env {
  // 获取到当前环境
  static const appEnv = String.fromEnvironment(EnvName.envKey);

  // 开发环境
  static final EnvConfig _debugConfig = EnvConfig(
      host: "http://129.211.191.80:10003/app",
      shareTopicUrl: "http://test.admin.dianzedushu.com/subjecttalk?id=%s",
      userPrivacy: "http://test.admin.dianzedushu.com/userprivacy",
      userAgreement: "http://test.admin.dianzedushu.com/useragreenment",
      rongYunKey: "lmxuhwagl66vd",
      // shareGroupUrl:
      //     "http://test.admin.dianzedushu.com/socialcontact?id=%s&inviterId=${Utils.getCurrentUserInfo().userId}",
      shareInviteUrl:
          "http://test.admin.dianzedushu.com?page=regist&inviteCode=%s&nickname=%s",
      shareDistributionUrl:
          "http://test.admin.dianzedushu.com/redirurl?id=%s&orderType=%s&agentId=%s",
      shareBookDetailUrl:
          "http://test.admin.dianzedushu.com/chapters?courseId=%s&agentId=%s",
      shareBookChapterUrl:
          "http://test.admin.dianzedushu.com/course?page=course&courseId=%s&chapterId=%s&type=%s&agentId=%s",
      shareActivityUrl: "http://test.admin.dianzedushu.com/signup?id=%s",
      sharePostUrl: "http://test.admin.dianzedushu.com/Invitation?postId=%s",
      partnerSpreadUrl: "http://test.admin.dianzedushu.com/partner",
  );

  // 后端本地服务器
  static final EnvConfig _debugServerConfig = EnvConfig(

      //周华生
      host: "http://192.168.1.162:10003/app",
      //官治民
      //host: "http://192.168.1.140:10004/app",
      shareTopicUrl: "http://test.admin.dianzedushu.com/subjecttalk?id=%s",
      userPrivacy: "http://test.admin.dianzedushu.com/userprivacy",
      userAgreement: "http://test.admin.dianzedushu.com/useragreenment",
      rongYunKey: "lmxuhwagl66vd",
      // shareGroupUrl:
      //     "http://test.admin.dianzedushu.com/socialcontact?id=%s&inviterId=${Utils.getCurrentUserInfo().userId}",
      shareInviteUrl:
          "http://test.admin.dianzedushu.com?page=regist&inviteCode=%s&nickname=%s",
      shareDistributionUrl:
          "http://test.admin.dianzedushu.com/redirurl?id=%s&orderType=%s&agentId=%s",
      shareBookDetailUrl:
          "http://test.admin.dianzedushu.com/chapters?courseId=%s",
      shareBookChapterUrl:
          "http://test.admin.dianzedushu.com/course?page=course&courseId=%s&chapterId=%s&type=%s",
      shareActivityUrl: "http://test.admin.dianzedushu.com/signup?id=%s",
      sharePostUrl: "http://test.admin.dianzedushu.com/Invitation?postId=%s");

  // 发布环境
  static final EnvConfig _releaseConfig = EnvConfig(
      host: "https://api.dzds.dianzedushu.com/app",
      shareTopicUrl: "https://www.dianzedushu.com/subjecttalk?id=%s",
      rongYunKey: "z3v5yqkbz22m0",
      shareInviteUrl:
          "https://www.dianzedushu.com?page=regist&inviteCode=%s&nickname=%s",
      shareActivityUrl: "https://www.dianzedushu.com/signup?id=%s",
      shareBookDetailUrl: "https://www.dianzedushu.com/chapters?courseId=%s",
      userPrivacy: "https://dianzedushu.com/userprivacy",
      userAgreement: "https://dianzedushu.com/useragreenment",
      // shareGroupUrl:
      //     "https://www.dianzedushu.com/socialcontact?id=%s&inviterId=${Utils.getCurrentUserInfo().userId}",
      shareBookChapterUrl:
          "https://www.dianzedushu.com/course?page=course&courseId=%s&chapterId=%s&type=%s",
      shareDistributionUrl:
          "https://www.dianzedushu.com/redirurl?id=%s&orderType=%s&agentId=%s",
      sharePostUrl: "https://www.dianzedushu.com/Invitation?postId=%s");

  static EnvConfig get envConfig => _getEnvConfig();

// 根据不同环境返回对应的环境配置
  static EnvConfig _getEnvConfig() {
    switch (appEnv) {
      case EnvName.debug:
        return _debugConfig;
      case EnvName.debugServer:
        return _debugServerConfig;
      case EnvName.release:
        return _releaseConfig;

      default:
        return _releaseConfig;
    }
  }
}

// 声明的环境
abstract class EnvName {
  // 环境key
  static const String envKey = "DART_DEFINE_APP_ENV";

  // 环境value
  static const String debug = "debug";
  static const String release = "release";

  static const String debugServer = "debugServer";
}