<template>
  <div>
    <el-menu :default-active="activeIndex" router class="el-menu-demo" mode="horizontal" active-text-color="#5BBDC9" @select="handleSelect">
      <el-menu-item index="1" :route="'/portal/home/course-detail/experiment?courseId=' + $route.query.courseId" >章节</el-menu-item>
      <el-menu-item index="2" :route="'/portal/home/course-detail/notice?courseId=' + $route.query.courseId">公告</el-menu-item>
      <el-menu-item index="3" :route="'/portal/home/course-detail/homework?courseId=' + $route.query.courseId">作业</el-menu-item>
      <el-menu-item index="4" :route="'/portal/home/course-detail/exam?courseId=' + $route.query.courseId">测试</el-menu-item>
      <el-menu-item index="5" :route="'/portal/home/course-detail/course-resource?courseId=' + $route.query.courseId">课件</el-menu-item>
    </el-menu>
    <router-view />
  </div>
</template>

<script>
export default {
  name: "CourseDetail",
  data(){
    return{
      activeIndex: '1',
    }
  },
  methods: {
    handleSelect(){

    }
  },
  mounted() {

  }
}
</script>

<style scoped>

</style>
