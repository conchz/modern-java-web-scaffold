import Vue from 'vue';
import template from './subscription-component-template.html';

const SubscriptionComponent = Vue.extend({
  template,
  props: [
    'pusher',
    'channel'
  ],
  created() {
    this.subscribeToChannel();
  },
  beforeDestroy() {
    this.unsubscribe();
  },
  watch: {
    'channel.active': function() {
      if (!this.channel.active && this.subscribed) {
        this.unsubscribe();
      } else if (this.channel.active && !this.subscribed) {
        this.subscribeToChannel();
      }
    }
  },
  data() {
    return {
      tweets: []
    }
  },
  methods: {
    unsubscribe() {
      this.pusherChannel.unsubscribe(btoa(this.channel.term));
      this.pusherChannel && this.pusherChannel.unbind();
      this.subscribed = false;
    },
    subscribeToChannel() {
      this.pusherChannel = this.pusher.subscribe(btoa(this.channel.term));
      this.pusherChannel.bind('new_tweet', (data) => {
        this.newTweet(data);
      });
      this.subscribed = true;
    },
    newTweet(data) {
      this.tweets.push(data);
      this.$nextTick(function() {
        const listItem = document.querySelector(`.channel-${this.channel.term}`);
        if (listItem) {
          listItem.scrollTop = listItem.scrollHeight;
        }
      });
    }
  }
});

export default SubscriptionComponent;
