let api = [];
api.push({
    alias: '7560f9b47f57c630514afd5e05c1e586',
    order: '1',
    link: 'hellocontroller',
    desc: 'HelloController',
    list: []
})
api[0].list.push({
    order: '1',
    desc: '',
});
api.push({
    alias: '856af79eb4b93c63b5885f0fe862c1d1',
    order: '2',
    link: '品牌商',
    desc: '品牌商',
    list: []
})
api[1].list.push({
    order: '1',
    desc: '品牌商分页列表',
});
api[1].list.push({
    order: '2',
    desc: '添加品牌商',
});
api[1].list.push({
    order: '3',
    desc: '通过ID修改品牌商',
});
api[1].list.push({
    order: '4',
    desc: '通过ID删除品牌商',
});
api[1].list.push({
    order: '5',
    desc: '通过ID获取品牌商详情',
});
api.push({
    alias: '88123981a88708dd3dcd92793256d5fa',
    order: '3',
    link: '商品分类表',
    desc: '商品分类表',
    list: []
})
api[2].list.push({
    order: '1',
    desc: '商品分类表分页列表',
});
api[2].list.push({
    order: '2',
    desc: '添加商品分类表',
});
api[2].list.push({
    order: '3',
    desc: '通过ID修改商品分类表',
});
api[2].list.push({
    order: '4',
    desc: '通过ID删除商品分类表',
});
api[2].list.push({
    order: '5',
    desc: '通过ID获取商品分类表详情',
});
api.push({
    alias: 'a0a4db14ac23f1c0574709f86e187140',
    order: '4',
    link: '常见问题表',
    desc: '常见问题表',
    list: []
})
api[3].list.push({
    order: '1',
    desc: '常见问题表分页列表',
});
api[3].list.push({
    order: '2',
    desc: '添加常见问题表',
});
api[3].list.push({
    order: '3',
    desc: '通过ID修改常见问题表',
});
api[3].list.push({
    order: '4',
    desc: '通过ID删除常见问题表',
});
api[3].list.push({
    order: '5',
    desc: '通过ID获取常见问题表详情',
});
api.push({
    alias: '6713d5345e0fcc31d588f38c50aaae70',
    order: '5',
    link: '后台用户',
    desc: '后台用户',
    list: []
})
api[4].list.push({
    order: '1',
    desc: '后台用户列表',
});
api[4].list.push({
    order: '2',
    desc: '添加',
});
api[4].list.push({
    order: '3',
    desc: '详情',
});
api[4].list.push({
    order: '4',
    desc: '编辑',
});
api[4].list.push({
    order: '5',
    desc: '删除',
});
api.push({
    alias: '85592f68fe1f31a39d056d382b860f2f',
    order: '6',
    link: 'admin认证相关',
    desc: 'admin认证相关',
    list: []
})
api[5].list.push({
    order: '1',
    desc: '登录',
});
api[5].list.push({
    order: '2',
    desc: '退出登录',
});
api[5].list.push({
    order: '3',
    desc: '',
});
api[5].list.push({
    order: '4',
    desc: '',
});
api[5].list.push({
    order: '5',
    desc: '获取登录用户信息',
});
api.push({
    alias: '96d9b55e9edd9ec4eaf5a2e86de73563',
    order: '7',
    link: 'admin系统配置',
    desc: 'Admin系统配置',
    list: []
})
api[6].list.push({
    order: '1',
    desc: '获取所有系统配置',
});
api[6].list.push({
    order: '2',
    desc: '保存设置',
});
api.push({
    alias: '1e4238151cacd1f691734553b0dd24ae',
    order: '8',
    link: 'hellocontroller',
    desc: 'HelloController',
    list: []
})
api[7].list.push({
    order: '1',
    desc: 'hello admin',
});
api.push({
    alias: '0378f86c774a5455deb73a20dc506c5e',
    order: '9',
    link: 'admin角色管理',
    desc: 'admin角色管理',
    list: []
})
api[8].list.push({
    order: '1',
    desc: '角色列表查询',
});
api[8].list.push({
    order: '2',
    desc: '删除',
});
api[8].list.push({
    order: '3',
    desc: '获取角色列表',
});
api[8].list.push({
    order: '4',
    desc: '角色添加',
});
api[8].list.push({
    order: '5',
    desc: '角色修改',
});
api[8].list.push({
    order: '6',
    desc: '获取角色详情',
});
api[8].list.push({
    order: '7',
    desc: '管理员的权限情况',
});
api[8].list.push({
    order: '8',
    desc: '更新管理员的权限',
});
api.push({
    alias: 'caea30429ac1d22108c7e170d883f92e',
    order: '10',
    link: '文件相关接口',
    desc: '文件相关接口',
    list: []
})
api[9].list.push({
    order: '1',
    desc: '附件分页列表',
});
api[9].list.push({
    order: '2',
    desc: '附件删除',
});
api[9].list.push({
    order: '3',
    desc: '上传文件',
});
api.push({
    alias: 'dict',
    order: '11',
    link: 'dict_list',
    desc: '数据字典',
    list: []
})
document.onkeydown = keyDownSearch;
function keyDownSearch(e) {
    const theEvent = e;
    const code = theEvent.keyCode || theEvent.which || theEvent.charCode;
    if (code == 13) {
        const search = document.getElementById('search');
        const searchValue = search.value;
        let searchArr = [];
        for (let i = 0; i < api.length; i++) {
            let apiData = api[i];
            const desc = apiData.desc;
            if (desc.indexOf(searchValue) > -1) {
                searchArr.push({
                    order: apiData.order,
                    desc: apiData.desc,
                    link: apiData.link,
                    list: apiData.list
                });
            } else {
                let methodList = apiData.list || [];
                let methodListTemp = [];
                for (let j = 0; j < methodList.length; j++) {
                    const methodData = methodList[j];
                    const methodDesc = methodData.desc;
                    if (methodDesc.indexOf(searchValue) > -1) {
                        methodListTemp.push(methodData);
                        break;
                    }
                }
                if (methodListTemp.length > 0) {
                    const data = {
                        order: apiData.order,
                        desc: apiData.desc,
                        link: apiData.link,
                        list: methodListTemp
                    };
                    searchArr.push(data);
                }
            }
        }
        let html;
        if (searchValue == '') {
            const liClass = "";
            const display = "display: none";
            html = buildAccordion(api,liClass,display);
            document.getElementById('accordion').innerHTML = html;
        } else {
            const liClass = "open";
            const display = "display: block";
            html = buildAccordion(searchArr,liClass,display);
            document.getElementById('accordion').innerHTML = html;
        }
        const Accordion = function (el, multiple) {
            this.el = el || {};
            this.multiple = multiple || false;
            const links = this.el.find('.dd');
            links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown);
        };
        Accordion.prototype.dropdown = function (e) {
            const $el = e.data.el;
            $this = $(this), $next = $this.next();
            $next.slideToggle();
            $this.parent().toggleClass('open');
            if (!e.data.multiple) {
                $el.find('.submenu').not($next).slideUp("20").parent().removeClass('open');
            }
        };
        new Accordion($('#accordion'), false);
    }
}

function buildAccordion(apiData, liClass, display) {
    let html = "";
    let doc;
    if (apiData.length > 0) {
        for (let j = 0; j < apiData.length; j++) {
            html += '<li class="'+liClass+'">';
            html += '<a class="dd" href="#_' + apiData[j].link + '">' + apiData[j].order + '.&nbsp;' + apiData[j].desc + '</a>';
            html += '<ul class="sectlevel2" style="'+display+'">';
            doc = apiData[j].list;
            for (let m = 0; m < doc.length; m++) {
                html += '<li><a href="#_' + apiData[j].order + '_' + doc[m].order + '_' + doc[m].desc + '">' + apiData[j].order + '.' + doc[m].order + '.&nbsp;' + doc[m].desc + '</a> </li>';
            }
            html += '</ul>';
            html += '</li>';
        }
    }
    return html;
}