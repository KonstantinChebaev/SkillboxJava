$(function() {

    var anim_id;

    var container = $('#container');
    var starship = $('#starship');
    var rock1 = $('#rock1');
    var rock2 = $('#rock2');
    var rock3 = $('#rock3');
    var rock4 = $('#rock4');
    var rock5 = $('#rock5');
    var rock6 = $('#rock6');

    var restart_div = $('#restart_div');
    var restart_btn = $('#restart');
    var score = $('#score');
    var high_score = localStorage.getItem('high_score');
    $('#high_score').text(high_score);

    var container_left = parseInt(container.css('left'));
    var container_width = parseInt(container.width());
    var container_height = parseInt(container.height());
    var ss_width = parseInt(starship.width());
    var ss_height = parseInt(starship.height());

    var game_over = false;

    var score_counter = 1;

    var speed = 2;
    var line_speed = 5;

    var move_right = false;
    var move_left = false;
    var move_up = false;
    var move_down = false;

    //---------------------------------
    $(document).on('keydown', function(e) {
        if (game_over === false) {
            var key = e.keyCode;
            if (key === 37 && move_left === false) {
                move_left = requestAnimationFrame(left);
            } else if (key === 39 && move_right === false) {
                move_right = requestAnimationFrame(right);
            } else if (key === 38 && move_up === false) {
                move_up = requestAnimationFrame(up);
            } else if (key === 40 && move_down === false) {
                move_down = requestAnimationFrame(down);
            }
        }
    });

    $(document).on('keyup', function(e) {
        if (game_over === false) {
            var key = e.keyCode;
            if (key === 37) {
                cancelAnimationFrame(move_left);
                move_left = false;
            } else if (key === 39) {
                cancelAnimationFrame(move_right);
                move_right = false;
            } else if (key === 38) {
                cancelAnimationFrame(move_up);
                move_up = false;
            } else if (key === 40) {
                cancelAnimationFrame(move_down);
                move_down = false;
            }
        }
    });

    function left() {
        if (game_over === false && parseInt(starship.css('left')) > 0) {
            starship.css('left', parseInt(starship.css('left')) - 5);
            move_left = requestAnimationFrame(left);
        }
    }

    function right() {
        if (game_over === false && parseInt(starship.css('left')) < container_width - ss_width) {
            starship.css('left', parseInt(starship.css('left')) + 5);
            move_right = requestAnimationFrame(right);
        }
    }

    function up() {
        if (game_over === false && parseInt(starship.css('top')) > 0) {
            starship.css('top', parseInt(starship.css('top')) - 3);
            move_up = requestAnimationFrame(up);
        }
    }

    function down() {
        if (game_over === false && parseInt(starship.css('top')) < container_height - ss_height) {
            starship.css('top', parseInt(starship.css('top')) + 3);
            move_down = requestAnimationFrame(down);
        }
    }
    /* Move the rocks and lines */

    anim_id = requestAnimationFrame(repeat);

    function repeat() {
        if (collision(starship, rock1) || collision(starship, rock2) || collision(starship, rock3)
            || collision(starship, rock4)|| collision(starship, rock5)|| collision(starship, rock6)) {
            stop_the_game();
            return;
        }

        score_counter++;

        if (score_counter % 20 == 0) {
            score.text(parseInt(score.text()) + 1);
        }
        if (score_counter % 400 == 0) {
            speed++;
            line_speed++;
        }

        rock_down(rock1);
        rock_down(rock2);
        rock_down(rock3);
        rock_down(rock4);
        rock_down(rock5);
        rock_down(rock6);

        anim_id = requestAnimationFrame(repeat);
    }

    function rock_down(rock) {
        var rock_current_top = parseInt(rock.css('top'));
        if (rock_current_top > container_height) {
            rock_current_top = -200;
            var rock_left = parseInt(Math.random() * (container_width - ss_width));
            rock.css('left', rock_left);
        }
        rock.css('top', rock_current_top + speed);
    }

    restart_btn.click(function() {
        location.reload();
    });

    function stop_the_game() {
        game_over = true;
        cancelAnimationFrame(anim_id);
        cancelAnimationFrame(move_right);
        cancelAnimationFrame(move_left);
        cancelAnimationFrame(move_up);
        cancelAnimationFrame(move_down);
        restart_div.slideDown();
        restart_btn.focus();
        setHighScore();
    }

    function setHighScore() {
        if (high_score < parseInt(score.text())) {
            high_score = parseInt(score.text());
            localStorage.setItem('high_score', parseInt(score.text()));
        }
        $('#high_score').text(high_score);
    }

    /* ------------------------------GAME CODE ENDS HERE------------------------------------------- */


    function collision($div1, $div2) {
        var x1 = $div1.offset().left;
        var y1 = $div1.offset().top;
        var h1 = $div1.outerHeight(true);
        var w1 = $div1.outerWidth(true);
        var b1 = y1 + h1;
        var r1 = x1 + w1;
        var x2 = $div2.offset().left;
        var y2 = $div2.offset().top;
        var h2 = $div2.outerHeight(true);
        var w2 = $div2.outerWidth(true);
        var b2 = y2 + h2;
        var r2 = x2 + w2;

        if (b1 < y2 || y1 > b2 || r1 < x2 || x1 > r2) return false;
        return true;
    }



});