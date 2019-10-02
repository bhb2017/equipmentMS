
$('#addstorageform').bootstrapValidator({
    message: 'This value is not valid',
    // 配置校验图标
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',    // 校验成功
        invalid: 'glyphicon glyphicon-remove',   // 校验失败
        validating: 'glyphicon glyphicon-refresh'  // 校验中
    },
    // 配置校验字段   (给input设置 name 值)
    fields: {
        materialId: {
            message: '没有器材id',
            validators: {
                notEmpty: {
                    message: '器材不能为空'
                },
                /*stringLength: {
                    min: 3,
                    max: 18,
                    message: '用户名长度必须在6到18位之间'
                },*/
                regexp: {
                    regexp: /^[0-9]+$/,
                    message: '器材id只能是数字'
                }
            }
        },
        place: {
            validators: {
                notEmpty: {
                    message: '地点不能为空'
                },
                stringLength: {
                    min: 1,
                    max: 18,
                    message: '地点长度必须在1到18位之间'
                },
                // regexp: {
                //     regexp: /^[a-zA-Z0-9_]+$/,
                //     message: '密码只能包含大写、小写、数字和下划线'
                // }
            }
        },
        num:{
            validators: {
                notEmpty: {
                    message: '数量不能为空'
                },
                regexp: { //正则表达式
                    //*星号表示可以重复任意次，也就是可以有多个号码
                    // (13|15|18)\d{9}$ 最后一个用来匹配没有分号的号码
                    // 最后一个号码不能有分号
                    regexp: /^[0-9]+$/,
                    //以13,15,18开头的手机号,共11位，如果多个手机号中间用英文的；（分号）分开
                    message: '数量格式不正确'
                },
            }
        },




    }
});
// $('#addForm').data("bootstrapValidator").resetForm(true)
$('#updatestorageform').bootstrapValidator({
    message: 'This value is not valid',
    // 配置校验图标
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',    // 校验成功
        invalid: 'glyphicon glyphicon-remove',   // 校验失败
        validating: 'glyphicon glyphicon-refresh'  // 校验中
    },
    // 配置校验字段   (给input设置 name 值)
    fields: {
        materialid: {
            message: '没有器材id',
            validators: {
                notEmpty: {
                    message: '器材不能为空'
                },
                /*stringLength: {
                    min: 3,
                    max: 18,
                    message: '用户名长度必须在6到18位之间'
                },*/
                regexp: {
                    regexp: /^[0-9]+$/,
                    message: '器材id只能是数字'
                }
            }
        },
        newplace: {
            validators: {
                notEmpty: {
                    message: '地点不能为空'
                },
                stringLength: {
                    min: 1,
                    max: 18,
                    message: '地点长度必须在1到18位之间'
                },
                // regexp: {
                //     regexp: /^[a-zA-Z0-9_]+$/,
                //     message: '密码只能包含大写、小写、数字和下划线'
                // }
            }
        },
        newnum:{
            validators: {
                notEmpty: {
                    message: '数量不能为空'
                },
                regexp: { //正则表达式
                    //*星号表示可以重复任意次，也就是可以有多个号码
                    // (13|15|18)\d{9}$ 最后一个用来匹配没有分号的号码
                    // 最后一个号码不能有分号
                    regexp: /^[0-9]+$/,
                    //以13,15,18开头的手机号,共11位，如果多个手机号中间用英文的；（分号）分开
                    message: '数量格式不正确'
                },
            }
        },




    }
});
