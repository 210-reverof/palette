import { memo } from 'react';
import { toast } from 'react-toastify';
import { motion } from 'framer-motion';
import { useMutation, useQueryClient } from '@tanstack/react-query';

import type { TimelineItem, User } from '@/@types';
import { Icon, Typography } from './common';
import type { IconKeyType } from './common/Icon';
import { apis } from '@/api';
import { useProfileId } from '@/hooks';

interface TimelineItemMenuProps {
  paintId: TimelineItem['id'];
  userId: User['id'];
  isMark: TimelineItem['marked'];
  username: User['username'];
  isFollowing?: boolean;
  onCloseMenu: VoidFunction;
}

function TimelineItemMenuItem({
  type,
  text,
  onClick,
}: {
  type: IconKeyType;
  text: string;
  onClick: VoidFunction;
}) {
  return (
    <li>
      <button
        type="button"
        onClick={(e) => {
          e.stopPropagation();
          onClick();
        }}
        className="w-full flex justify-between px-[14px] py-[12px] transition-colors hover:bg-grey-200 rounded-t-[12px] cursor-pointer"
      >
        <Typography size="body-1" color="black">
          {text}
        </Typography>
        <Icon type={type} width={22} height={22} />
      </button>
    </li>
  );
}

function TimelineItemMenu({
  paintId,
  username,
  isMark,
  userId,
  isFollowing,
  onCloseMenu,
}: TimelineItemMenuProps) {
  const profileId = useProfileId();
  const queryClient = useQueryClient();

  const followMutation = useMutation({
    mutationKey: ['user-follow', userId],
    mutationFn: () => apis.users.followUser(userId),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['paint'] });
      toast('팔로잉이 되었습니다.');
    },
  });

  const unFollowMutation = useMutation({
    mutationKey: ['user-unFollow', userId],
    mutationFn: () => apis.users.unFollowUser(userId),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['paint'] });
      toast('팔로잉이 취소 되었습니다.');
    },
  });

  const markPaintMutation = useMutation({
    mutationKey: ['mark-paint', userId, paintId],
    mutationFn: () => apis.users.markPaint({ userId, paintId }),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['paint', 'bookmark'] });
      toast('북마크가 되었습니다.');
    },
  });

  const unMarkMutation = useMutation({
    mutationKey: ['unmark-paint', userId, paintId],
    mutationFn: () => apis.users.unMarkPaint({ userId, paintId }),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['paint', 'bookmark'] });
      toast('팔로잉이 취소 되었습니다.');
    },
  });

  const handleClickNotSupport = () => {
    toast('아직 지원되지 않는 기능입니다.');
    onCloseMenu();
  };

  return (
    <motion.ul
      role="menu"
      className="flex flex-col bg-white z-[50] rounded-[12px] w-[232px] absolute top-[32px] right-0"
      initial={{ opacity: 0 }}
      animate={{ opacity: 100 }}
    >
      <TimelineItemMenuItem
        type="frown"
        text="이 게시물에 관심 없음"
        onClick={handleClickNotSupport}
      />
      <span role="none" className="w-full h-[7px] bg-grey-200" />
      <div className="divide-y divide-grey-200">
        {profileId !== userId && (
          <TimelineItemMenuItem
            type={isFollowing ? 'userMinus' : 'userPlus'}
            text={`${username}님 ${
              isFollowing ? '언팔로우하기' : '팔로우 하기'
            }`}
            onClick={() => {
              if (isFollowing) {
                unFollowMutation.mutate();
              } else {
                followMutation.mutate();
              }
              onCloseMenu();
            }}
          />
        )}
        <TimelineItemMenuItem
          type="list"
          text="리스트에 추가 삭제"
          onClick={handleClickNotSupport}
        />
        <TimelineItemMenuItem
          type="list"
          text={`${isMark ? '북마크 취소하기' : '북마크 하기'}`}
          onClick={() => {
            if (isMark) {
              unMarkMutation.mutate();
            } else {
              markPaintMutation.mutate();
            }
            onCloseMenu();
          }}
        />
        <TimelineItemMenuItem
          type="mute"
          text={`${username} 님 뮤트하기`}
          onClick={handleClickNotSupport}
        />
        <TimelineItemMenuItem
          type="stop"
          text={`${username} 님 차단하기`}
          onClick={handleClickNotSupport}
        />
        <TimelineItemMenuItem
          type="flag"
          text="게시물 신고하기"
          onClick={handleClickNotSupport}
        />
      </div>
    </motion.ul>
  );
}

const MemoizedTimelineItemMenu = memo(TimelineItemMenu);

export default MemoizedTimelineItemMenu;
