<script>
export default {
  data() {
    return {
      show: true,
      list: [],
      note: {
        message: "",
        top: "0",
        left: "0",
      },
    };
  },
  methods: {
    // Creating function
    append: function () {
      if (this.note.message) {
        let clonedNote = Object.assign({}, this.note);
        this.list.push(clonedNote);
        this.note.message = "";
      }
    },
  },
};
</script>

<template>
  <p>Message is: {{ note.message }}</p>
  <input v-model="note.message" placeholder="New text" required />
  <input v-model="note.top" placeholder="Top" required />
  <input v-model="note.left" placeholder="Right" required />

  <button @click="show = !show">Toggle List</button>
  <button @click="append()">Push Message</button>
  <button @click="list.pop()">Pop Number</button>
  <button @click="list.reverse()">Reverse List</button>

  <ul v-if="show && list.length">
    <li v-for="item of list" :key="item">{{ item.message }}</li>
  </ul>
  <p v-else-if="list.length">List is not empty, but hidden.</p>
  <p v-else>List is empty.</p>
  <div class="relative">
    <div
      v-for="noteItem of list"
      :key="noteItem"
      class="absolute"
      :style="{ top: noteItem.top + 'px', left: noteItem.left + 'px' }"
    >
      {{ noteItem.message }}
    </div>
  </div>
</template>

<style scoped>
div.relative {
  position: relative;
  width: 400px;
  height: 200px;
  border: 3px solid #73ad21;
}
div.absolute {
  position: absolute;
  width: 200px;
  height: 100px;
  border: 3px solid #73ad21;
}
</style>
