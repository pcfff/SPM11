<template>
  <div class="list-wrapper">
    <el-card class="data-item" v-for="item in tableList" :key="item.id">
      <div class="header">
        <svg t="1702798640789" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"
             p-id="6720" width="32" height="32">
          <path
              d="M128 0h64v128a64 64 0 0 0 64 64h512a64 64 0 0 0 64-64V0h64a128 128 0 0 1 128 128v768a128 128 0 0 1-128 128H128a128 128 0 0 1-128-128V128a128 128 0 0 1 128-128z m641.152 355.776l-303.936 303.936-175.872-205.184-72.896 62.464 243.328 283.904 377.216-377.216-67.84-67.904zM768 0v64a64 64 0 0 1-64 64H320a64 64 0 0 1-64-64V0h512z"
              fill="#5BBCC9" p-id="6721"></path>
        </svg>
        <div class="header-info-wrapper">
          <p class="title">{{ item.name }}</p>
        </div>
      </div>
      <div class="info-wrapper">
        <video :src="$baseURL + item.fileUrl" controls></video>
        <p>发布时间：{{ $moment(item.createTime, 'yyyy.MM.DD') }}</p>
      </div>
      <div>
        <Comment :comments="item.commentList" :replyId="replyUser.commentId" @reply="reply" />
        <div class="comment-wrapper">
          <el-input v-model="commentVal" :placeholder="replyUser.username ? '回复' + replyUser.username : '请输入评论内容'" ></el-input>
          <el-button type="primary" @click="submitComment(item.id, item)">发布
          </el-button>
        </div>
      </div>
    </el-card>

    <el-pagination
        background
        layout="prev, pager, next"
        :total="total"
        @current-change="changePage"
    >
    </el-pagination>
  </div>
</template>

<script>


import Comment from "@/components/Comment.vue";

export default {
  components: {Comment},
  data() {
    return {
      tableList: [],
      pageInfo: {
        pageNum: 1,
        pageSize: 10,
      },
      commentVal: '',
      replyUser: {
        commentId: 0,
        username: '',
      },
      total: 0,
      dialogFormVisible: false
    }
  },
  methods: {
    changePage(e) {
      this.pageInfo.pageNum = e
      this.getTableList()
    },
    getCommentList(experimentId, item){
      this.$axios.get('/experiment-comment/list', {
        params: {
          experimentId: experimentId
        }
      }).then(res => {
        item.commentList = res.data
        this.$forceUpdate()
      })
    },
    getTableList() {
      this.$axios.get('/experiment/list', {
        params: Object.assign({
          courseId: this.$route.query.courseId
        }, this.pageInfo)
      }).then(res => {
        this.tableList = res.data.rows.map(item => {
          item.commentList = []
          this.getCommentList(item.id, item)
          return item
        })
        this.total = res.data.count;
      })
    },
    reply(commentId, username){
      this.replyUser.commentId = commentId
      this.replyUser.username = username
    },
    submitComment(experimentId, item){
      if(this.commentVal){
        this.$axios.post('/experiment-comment/add', {
          experimentId: experimentId,
          replyId: this.replyUser.commentId ? this.replyUser.commentId : null,
          content: this.commentVal
        }).then(res => {
          if (res.success){
            this.commentVal = ''
            this.replyUser.commentId = 0
            this.replyUser.username = ''
            this.getCommentList(experimentId, item)
          }else {
            this.$message.error('评论失败')
          }
        })
      }else {
        this.$message.error('请输入评论内容')
      }
    }
  },
  mounted() {
    this.getTableList()
  }
}
</script>

<style scoped lang="scss">
.data-item {
  margin-bottom: 10px;

  .header {
    display: flex;
    align-items: center;
    justify-content: flex-start;

    .header-info-wrapper {
      margin-left: 10px;

      .title {
        font-size: 20px;
        font-weight: bold;
      }

      p {
        margin: 0;
      }
    }
  }

  .info-wrapper {
    margin: 20px 0;

    video {
      width: 100%;
    }
  }

  .comment-wrapper{
    display: flex;
    align-items: center;
    justify-content: center;
  }
}

.el-pagination {
  margin-top: 10px;
}
</style>
