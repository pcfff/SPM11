<template>
  <div class="comment-list">
    <!-- 主评论 -->
    <div v-for="(comment, index) in comments" :key="index" class="comment-item">
      <div class="comment-info">
        <div class="comment-avatar">
          <el-avatar size="large">
            <img src="../assets/avatar.jpg" alt="Avatar">
          </el-avatar>
          <p class="comment-name">{{ comment.username }}</p>
        </div>
        <div class="comment-info-wrapper">
          <p class="comment-content">{{ comment.content }}</p>
          <p class="comment-time">{{ $moment(comment.createTime, 'yyyy.MM.DD HH:mm:ss') }}</p>
          <el-link type="primary" @click="comment.id === replyId ? $emit('reply', 0, '') : $emit('reply', comment.id, comment.username)">{{ comment.id === replyId ? '取消回复' : '回复' }}</el-link>
        </div>
      </div>
      <!-- 子评论 -->
      <div v-if="comment.commentList && comment.commentList.length" class="sub-comments">
        <div v-for="(reply, idx) in comment.commentList" :key="idx" class="comment-item">
          <div class="comment-info">
            <div class="comment-avatar">
              <el-avatar size="medium">
                <img src="../assets/avatar.jpg" alt="Avatar">
              </el-avatar>
              <p class="comment-name">{{ reply.username }}</p>
            </div>
            <div class="comment-info-wrapper">
              <p class="comment-content">{{ reply.content }}</p>
              <p class="comment-time">{{ $moment(reply.createTime, 'yyyy.MM.DD HH:mm:ss') }}</p>
              <el-link type="primary" @click="reply.id === replyId ? $emit('reply', 0, '') : $emit('reply', comment.id, reply.username)">{{ reply.id === replyId ? '取消回复' : '回复' }}</el-link>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Comment",
  props: {
    comments: {
      type: Array,
      default: () => []
    },
    replyId: {
      type: Number,
      default: 0
    }
  },
  data() {
    return {

    };
  },
  methods: {

  }
};
</script>

<style scoped lang="scss">
.comment-list {
  width: 100%;
  box-sizing: border-box;
}

.comment-item {
  display: flex;
  flex-direction: column;
  margin-bottom: 20px;
  border: 1px solid #ccc;
  padding: 10px 20px;
  border-radius: 5px;

  .comment-info {
    display: flex;
    flex-direction: column;
    flex: 1;

    .comment-avatar {
      margin-right: 10px;
      display: flex;
      align-items: center;
      justify-content: flex-start;

      img {
        width: 50px;
        height: 50px;
        border-radius: 50%;
        object-fit: cover;
      }

      .comment-name {
        font-weight: bold;
        margin-left: 10px;
      }


    }

    .comment-info-wrapper {
      flex: 1;

      .comment-content {
        margin: 0;
        margin-bottom: 5px;
      }

      .comment-time {
        color: #888;
        font-size: 0.9rem;
      }

      el-link {
        margin-top: 5px;
        display: inline-block;
      }
    }
  }

  .sub-comments {
    width: 100%;
    margin-top: 10px;
    padding-left: 60px;

    .comment-item {
      margin-bottom: 10px;
      border: none;
      padding: 0;
      border-radius: 0;

      .comment-info {
        .comment-avatar {
          img {
            width: 40px;
            height: 40px;
          }
        }

        .comment-info-wrapper {
          .comment-name {
            font-size: 0.9rem;
          }
        }
      }
    }
  }
}

</style>
