
$('form').bootstrapValidator({
    message: 'This value is not valid',
    // 配置校验图标
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',    // 校验成功
        invalid: 'glyphicon glyphicon-remove',   // 校验失败
        validating: 'glyphicon glyphicon-refresh'  // 校验中
    },
    // 配置校验字段   (给input设置 name 值)
    fields: {
        name: {
            message: '器材名验证失败',
            validators: {
                notEmpty: {
                    message: '器材名不能为空'
                }
            }
        },
        specification: {
            validators: {
                notEmpty: {
                    message: '规格不能为空'
                }
            }
        },
        newspecification:{
            validators: {
                notEmpty: {
                    message: '规格不能为空'
                }
            }
        },

        price: {
            message: '单价格式错误',
            validators: {
                notEmpty: {
                    message: '单价不能为空'
                },
                regexp: { //正则表达式
                    //*星号表示可以重复任意次，也就是可以有多个号码
                    // (13|15|18)\d{9}$ 最后一个用来匹配没有分号的号码
                    // 最后一个号码不能有分号
                    regexp: /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/,

                    //以13,15,18开头的手机号,共11位，如果多个手机号中间用英文的；（分号）分开
                    message: '单价格式不正确'
                },
            }
        },
        newprice:{
            message: '单价格式错误',
            validators: {
                notEmpty: {
                    message: '单价不能为空'
                },
                regexp: { //正则表达式
                    //*星号表示可以重复任意次，也就是可以有多个号码
                    // (13|15|18)\d{9}$ 最后一个用来匹配没有分号的号码
                    // 最后一个号码不能有分号
                    regexp: /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/,
                    //以13,15,18开头的手机号,共11位，如果多个手机号中间用英文的；（分号）分开
                    message: '单价格式不正确'
                },
            }
        },
        rate: {
            message: '损耗率格式错误',
            validators: {
                notEmpty: {
                    message: '损耗率不能为空'
                },
                regexp: { //正则表达式
                    // 身份证号码为15位或者18位，15位时全为数字，
                    // 18位前17位为数字，最后一位是校验位，可能为数字或字符X
                    regexp: /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/,

                    message: '损耗率格式不正确'
                },
            }
        },
        newrate:{
            message: '损耗率格式错误',
            validators: {
                notEmpty: {
                    message: '损耗率不能为空'
                },
                regexp: { //正则表达式
                    // 身份证号码为15位或者18位，15位时全为数字，
                    // 18位前17位为数字，最后一位是校验位，可能为数字或字符X
                    regexp: /^[+]{0,1}(\d+)$|^[+]{0,1}(\d+\.\d+)$/,

                    message: '损耗率格式不正确'
                },
            }
        }

    }
});
// $('#addForm').data("bootstrapValidator").resetForm(true)
