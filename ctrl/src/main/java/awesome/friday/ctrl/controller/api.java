

/**
 * @api {post} /login 登录
 * @apiName 登录
 * @apiGroup Main
 *
 *
 * @apiParam {String} username username
 * @apiParam {String} password password
 * @apiParam {String} uuid 唯一ID
 * @apiParam {String} text 验证码内容
 *
 *
 * @apiParamExample {json} Request-Example:
 * {
 *     "username":"369",
 *     "password":"5599",
 *     "uuid":"b34797fe-70e4-4077-8620-263ac420c607",
 *     "text":"pnyn"
 * }
 * @apiSuccessExample {json} Success-Response:
 * HTTP/1.1 200 OK
 * {
 *    "conde": 0,
 *    "data": "369"
 * }
 *
 * @apiVersion 0.0.1
 *
 *
 */

/**
 * @api {post} /new 创建
 * @apiName 创建
 * @apiGroup Main
 *
 *
 * @apiParam {String} username username
 * @apiParam {String} password password
 *
 *
 * @apiParamExample {json} Request-Example:
 * {
 *     "username":"369",
 *     "password":"5599",
 * }
 *
 * @apiSuccessExample {json} Success-Response:
 * HTTP/1.1 200 OK
 * {
 *    "conde": 0,
 *    "data": "369"
 * }
 *
 * @apiVersion 0.0.1
 *
 *
 */

/**
 * @api {get} /captcha/:uuid 获取验证码
 * @apiName 获取验证码
 * @apiGroup Main
 *
 *
 * @apiParam {String} username username
 * @apiParam {String} password password
 *
 *
 * @apiParamExample {json} Request-Example:
 * localhost:9000/captcha/b34797fe-70e4-4077-8620-263ac420c607
 *
 *
 * @apiVersion 0.0.1
 */
