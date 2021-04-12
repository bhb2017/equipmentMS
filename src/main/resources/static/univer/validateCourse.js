
$('#courseplanform').bootstrapValidator({
    message: 'This value is not valid',
    // 配置校验图标
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',    // 校验成功
        invalid: 'glyphicon glyphicon-remove',   // 校验失败
        validating: 'glyphicon glyphicon-refresh'  // 校验中
    },
    // 配置校验字段   (给input设置 name 值)
    fields: {
        num: {
            message: '开课人数验证失败',
            validators: {
                notEmpty: {
                    message: '开课人数不能为空'
                },
                // stringLength: {
                //     min: 3,
                //     max: 18,
                //     message: '用户名长度必须在6到18位之间'
                // },
                regexp: {
                    regexp: /^[0-9]+$/,
                    message: '开课人数只能为正整数'
                }
            }
        },
        schoolTime: {
            validators: {
                notEmpty: {
                    message: '开课时间不能为空'
                },
                // stringLength: {
                //     min: 3,
                //     max: 18,
                //     message: '密码长度必须在6到18位之间'
                // },
                // regexp: {
                //     regexp: /^[a-zA-Z0-9_]+$/,
                //     message: '密码只能包含大写、小写、数字和下划线'
                // }
            }
        },
        // phone:{
        //     validators: {
        //         notEmpty: {
        //             message: '号码不能为空'
        //         },
        //         regexp: { //正则表达式
        //             //*星号表示可以重复任意次，也就是可以有多个号码
        //             // (13|15|18)\d{9}$ 最后一个用来匹配没有分号的号码
        //             // 最后一个号码不能有分号
        //             regexp: /^((13|15|18)\d{9}\;)*(13|15|18)\d{9}$/,
        //             //以13,15,18开头的手机号,共11位，如果多个手机号中间用英文的；（分号）分开
        //             message: '号码格式不正确'
        //         },
        //     }
        // },




    }
});
// $('#addForm').data("bootstrapValidator").resetForm(true)
$('#courseform').bootstrapValidator({
    message: 'This value is not valid',
    // 配置校验图标
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',    // 校验成功
        invalid: 'glyphicon glyphicon-remove',   // 校验失败
        validating: 'glyphicon glyphicon-refresh'  // 校验中
    },
    // 配置校验字段   (给input设置 name 值)
    fields: {
        courseName: {
            message: '课程名验证失败',
            validators: {
                notEmpty: {
                    message: '开课名不能为空'
                },
                stringLength: {
                    min: 1,
                    max: 18,
                    message: '课程名长度必须在6到18位之间'
                },
                // regexp: {
                //     regexp: /^[0-9]+$/,
                //     message: '开课人数只能为正整数'
                // }
            }
        },
        courseNo: {
            validators: {
                notEmpty: {
                    message: '课程编号不能为空'
                },
                stringLength: {
                    min: 1,
                    max: 18,
                    message: '课程编号长度必须在6到18位之间'
                },
                // regexp: {
                //     regexp: /^[a-zA-Z0-9_]+$/,
                //     message: '密码只能包含大写、小写、数字和下划线'
                // }
            }
        },
        // phone:{
        //     validators: {
        //         notEmpty: {
        //             message: '号码不能为空'
        //         },
        //         regexp: { //正则表达式
        //             //*星号表示可以重复任意次，也就是可以有多个号码
        //             // (13|15|18)\d{9}$ 最后一个用来匹配没有分号的号码
        //             // 最后一个号码不能有分号
        //             regexp: /^((13|15|18)\d{9}\;)*(13|15|18)\d{9}$/,
        //             //以13,15,18开头的手机号,共11位，如果多个手机号中间用英文的；（分号）分开
        //             message: '号码格式不正确'
        //         },
        //     }
        // },




    }
});

$('#updateCourseform').bootstrapValidator({
    message: 'This value is not valid',
    // 配置校验图标
    feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',    // 校验成功
        invalid: 'glyphicon glyphicon-remove',   // 校验失败
        validating: 'glyphicon glyphicon-refresh'  // 校验中
    },
    // 配置校验字段   (给input设置 name 值)
    fields: {
        newcourseName: {
            message: '课程名验证失败',
            validators: {
                notEmpty: {
                    message: '开课名不能为空'
                },
                stringLength: {
                    min: 1,
                    max: 18,
                    message: '课程名长度必须在6到18位之间'
                },
                // regexp: {
                //     regexp: /^[0-9]+$/,
                //     message: '开课人数只能为正整数'
                // }
            }
        },
        newcourseNo: {
            validators: {
                notEmpty: {
                    message: '课程编号不能为空'
                },
                stringLength: {
                    min: 1,
                    max: 18,
                    message: '课程编号长度必须在6到18位之间'
                },
                // regexp: {
                //     regexp: /^[a-zA-Z0-9_]+$/,
                //     message: '密码只能包含大写、小写、数字和下划线'
                // }
            }
        },
        // phone:{
        //     validators: {
        //         notEmpty: {
        //             message: '号码不能为空'
        //         },
        //         regexp: { //正则表达式
        //             //*星号表示可以重复任意次，也就是可以有多个号码
        //             // (13|15|18)\d{9}$ 最后一个用来匹配没有分号的号码
        //             // 最后一个号码不能有分号
        //             regexp: /^((13|15|18)\d{9}\;)*(13|15|18)\d{9}$/,
        //             //以13,15,18开头的手机号,共11位，如果多个手机号中间用英文的；（分号）分开
        //             message: '号码格式不正确'
        //         },
        //     }
        // },




    }
});
