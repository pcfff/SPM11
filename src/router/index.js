import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const router = new Router({
    routes: [
        {
            path: '/',
            redirect: '/login'
        },
        {
            path: '/login',
            component: () => import('@/pages/Login.vue'),
        },
        {
            path: '/portal',
            component: () => import('@/layout/Portal.vue'),
            redirect: '/portal/home',
            children: [
                {
                    path: '/portal/home',
                    redirect: '/portal/home/index',
                    component: () => import('@/pages/portal/home/Home.vue'),
                    children: [
                        {
                            path: '/portal/home/index',
                            component: () => import('@/pages/portal/home/Index.vue'),
                        },
                        {
                            path: '/portal/home/course',
                            component: () => import('@/pages/portal/home/Course.vue'),
                        },
                        {
                            path: '/portal/home/notice',
                            component: () => import('@/pages/portal/home/Notice.vue'),
                        },
                        {
                            path: '/portal/home/course-detail',
                            component: () => import('@/pages/portal/home/coursedetail/CourseDetail.vue'),
                            redirect: '/portal/home/course-detail/experiment',
                            children: [
                                {
                                    path: '/portal/home/course-detail/experiment',
                                    component: () => import('@/pages/portal/home/coursedetail/Experiment.vue'),
                                },
                                {
                                    path: '/portal/home/course-detail/notice',
                                    component: () => import('@/pages/portal/home/coursedetail/Notice.vue'),
                                },
                                {
                                    path: '/portal/home/course-detail/homework',
                                    component: () => import('@/pages/portal/home/coursedetail/Homework.vue'),
                                },
                                {
                                    path: '/portal/home/course-detail/homework-detail',
                                    component: () => import('@/pages/portal/home/coursedetail/HomeworkDetail.vue'),
                                },
                                {
                                    path: '/portal/home/course-detail/exam',
                                    component: () => import('@/pages/portal/home/coursedetail/Exam.vue'),
                                },
                                {
                                    path: '/portal/home/course-detail/course-resource',
                                    component: () => import('@/pages/portal/home/coursedetail/CourseResource.vue'),
                                }
                            ]
                        },
                        {
                            path: '/portal/user/updatepwd',
                            component: () => import('@/pages/portal/user/UpdatePwd.vue'),
                        },
                        {
                            path: '/portal/user/info',
                            component: () => import('@/pages/portal/user/UserInfo.vue'),
                        },
                    ]
                }
            ]
        },
        {
            path: '/main',
            component: () => import('@/layout/Main.vue'),
            redirect: '/main/student/list',
            children: [
                {
                    path: '/main/student/list',
                    component: () => import('@/pages/backend/student/List.vue'),
                },
                {
                    path: '/main/student/add',
                    component: () => import('@/pages/backend/student/Add.vue'),
                },
                {
                    path: '/main/student/modify',
                    component: () => import('@/pages/backend/student/Modify.vue'),
                },
                {
                    path: '/main/course/list',
                    component: () => import('@/pages/backend/course/List.vue'),
                },
                {
                    path: '/main/course/add',
                    component: () => import('@/pages/backend/course/Add.vue'),
                },
                {
                    path: '/main/course/modify',
                    component: () => import('@/pages/backend/course/Modify.vue'),
                },

                {
                    path: '/main/question/list',
                    component: () => import('@/pages/backend/question/List.vue'),
                },
                {
                    path: '/main/question/add',
                    component: () => import('@/pages/backend/question/Add.vue'),
                },
                {
                    path: '/main/question/modify',
                    component: () => import('@/pages/backend/question/Modify.vue'),
                },
                {
                    path: '/main/homework/list',
                    component: () => import('@/pages/backend/homework/Index.vue'),
                },
                {
                    path: '/main/homework/add',
                    component: () => import('@/pages/backend/homework/Add.vue'),
                },
                {
                    path: '/main/homework/modify',
                    component: () => import('@/pages/backend/homework/Modify.vue'),
                }, {
                    path: '/main/homework-result/list',
                    component: () => import('@/pages/backend/homework/Result.vue'),
                }, {
                    path: '/main/homework-submit/detail',
                    component: () => import('@/pages/backend/homework/SubmitDetail.vue'),
                },

                {
                    path: '/main/experiment/list',
                    component: () => import('@/pages/backend/experiment/List.vue'),
                },
                {
                    path: '/main/experiment/add',
                    component: () => import('@/pages/backend/experiment/Add.vue'),
                },
                {
                    path: '/main/experiment/modify',
                    component: () => import('@/pages/backend/experiment/Modify.vue'),
                },

                {
                    path: '/main/notice/list',
                    component: () => import('@/pages/backend/notice/List.vue'),
                },
                {
                    path: '/main/notice/add',
                    component: () => import('@/pages/backend/notice/Add.vue'),
                },
                {
                    path: '/main/notice/modify',
                    component: () => import('@/pages/backend/notice/Modify.vue'),
                },

                {
                    path: '/main/course-resource/list',
                    component: () => import('@/pages/backend/course-resource/List.vue'),
                },
                {
                    path: '/main/course-resource/add',
                    component: () => import('@/pages/backend/course-resource/Add.vue'),
                },
                {
                    path: '/main/course-resource/modify',
                    component: () => import('@/pages/backend/course-resource/Modify.vue'),
                },

                {
                    path: '/main/experiment-comment/list',
                    component: () => import('@/pages/backend/experiment-comment/List.vue'),
                },

                {
                    path: '/main/settins/index',
                    component: () => import('@/pages/backend/settings/Index.vue'),
                },

                {
                    path: '/main/user/updatepwd',
                    component: () => import('@/pages/backend/user/UpdatePwd.vue'),
                },
                {
                    path: '/main/user/info',
                    component: () => import('@/pages/backend/user/UserInfo.vue'),
                },
            ]
        },
    ]
})

router.beforeEach((to, from, next) => {
    if (to.path === '/login' || to.path === '/register' || to.path === '/') {
        next();
    } else {
        let token = localStorage.getItem('token');
        if (token === null || token === '') {
            next('/login');
        } else {
            next();
        }
    }
})

export default router
